package cn.ac.iscas.controller;

import cn.ac.iscas.dao.MeteorologicalInfoDao;
import cn.ac.iscas.dao.MeteorologicalStationDao;
import cn.ac.iscas.entity.MeteorologicalInfo;
import cn.ac.iscas.entity.MeteorologicalStation;
import ucar.ma2.ArrayFloat;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
import ucar.nc2.dataset.NetcdfDataset;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by admin on 2017/6/2.
 */
public class NoaaSPFHController {
    MeteorologicalInfoDao meteorologicalInfoDao;
    MeteorologicalStationDao meteorologicalStationDao;

    public NoaaSPFHController() {
        meteorologicalInfoDao = new MeteorologicalInfoDao();
        meteorologicalStationDao = new MeteorologicalStationDao();
    }

    public static void main(String[] args) {
        MeteorologicalInfoController meteorologicalInfoController = new MeteorologicalInfoController();
        NoaaSPFHController noaaSPFHController = new NoaaSPFHController();
        noaaSPFHController.handle_spfh(meteorologicalInfoController);
    }

    public void handle_spfh(MeteorologicalInfoController meteorologicalInfoController) {
        String fileName = "D:\\Project\\Stem\\noaa\\spfh_2m_latlon_all_20020101_20161231_lele9CWgUO.nc";
        List<MeteorologicalStation> meteorologicalStations = meteorologicalInfoController.meteorologicalStationDao.getAllByIdLike("53%");
        NetcdfFile ncFile = null;
        try {
            ncFile = NetcdfDataset.open(fileName);
            String var1 = "lat";
            String var2 = "lon";
            Variable latVar = ncFile.findVariable(var1);
            if (latVar == null) {
                System.out.println("Can't find Variable latitude");
                return;
            }
            Variable lonVar = ncFile.findVariable(var2);
            if (lonVar == null) {
                System.out.println("Can't find Variable longitude");
                return;
            }
            // Get the lat/lon data from the file.
            ArrayFloat.D1 latArray;
            ArrayFloat.D1 lonArray;

            latArray = (ArrayFloat.D1) latVar.read();
            lonArray = (ArrayFloat.D1) lonVar.read();

            // Check the coordinate variable data.
            for (int lat = 0; lat < 10; lat++)
                if (latArray.get(lat) != 20 + 1. * lat)
                    System.err.println("ERROR incorrect value in variable latitude");

            for (int lon = 0; lon < 11; lon++)
                if (lonArray.get(lon) != 96 + 1. * lon)
                    System.err.println("ERROR incorrect value in variable longtitude");

            //Get the fhour 、time and ens variables.
            String var3 = "fhour";
            Variable fhourVar = ncFile.findVariable(var3);
            if (fhourVar == null) {
                System.out.println("Can't find Variable fhour");
                return;
            }
            String var4 = "time";
            Variable timeVar = ncFile.findVariable(var4);
            if (timeVar == null) {
                System.out.println("Can't find Variable time");
                return;
            }
            String var5 = "ens";
            Variable ensVar = ncFile.findVariable(var5);
            if (ensVar == null) {
                System.out.println("Can't find Variable ens");
                return;
            }

            String var6 = "Specific_humidity_height_above_ground";
            Variable humidityVar = ncFile.findVariable(var6);
            if (humidityVar == null) {
                System.out.println("Can't find Variable Specific_humidity_height_above_ground");
                return;
            }
            int time = 0;
            int ens = 0;
            int fhour = 0;
            int lat = 0;
            int lon = 0;
            float BASE_LAT = (float) 19.5;
            float BASE_LON = (float) 95.5;
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

            Date udate = format.parse("20020101");
            Calendar calendar = new GregorianCalendar();
            ArrayFloat.D5 data5D = (ArrayFloat.D5) humidityVar.read();
            for (time = 0; time < 5479; time++) {
                for (MeteorologicalStation meteorologicalStation : meteorologicalStations) {
                    java.sql.Date date = new java.sql.Date(udate.getTime());
                    lat = (int) (meteorologicalStation.getLat() - BASE_LAT);
                    lon = (int) (meteorologicalStation.getLon() - BASE_LON);
                    MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
                    meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
                    meteorologicalInfo.setDate(date);
                    meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
                    float value = data5D.get(time, ens, fhour, lat, lon);
                    DecimalFormat decimalFormat = new DecimalFormat("0.00000");
                    meteorologicalInfo.setNoaa_spfh(decimalFormat.format(value));
                    meteorologicalInfoDao.saveEntity(meteorologicalInfo);
                    System.out.println(meteorologicalStation + " " + date + "  lat:" + lat + "  lon:" + lon + " value:" + meteorologicalInfo.getNoaa_spfh() + "  " + value);
                }
                calendar.setTime(udate);
                calendar.add(calendar.DATE, 1);
                udate = calendar.getTime();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (ncFile != null)
                try {
                    ncFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
}

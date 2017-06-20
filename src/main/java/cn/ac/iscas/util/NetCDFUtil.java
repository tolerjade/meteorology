package cn.ac.iscas.util;

import ucar.ma2.ArrayFloat;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
import ucar.nc2.dataset.NetcdfDataset;

import java.io.IOException;

/**
 * Created by admin on 2017/6/1.
 */
public class NetCDFUtil {
    public static void main(String[] args) {
        String fileName = "D:\\Project\\Stem\\noaa\\spfh_2m_latlon_all_20020101_20161231_lele9CWgUO.nc";
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
            int[] origin = new int[]{1, 1, 1, 1, 1};
            int[] size = new int[]{11, 10, 1, 1, 1};
            int time = 0;
            int ens = 0;
            int fhour = 0;
            int lat = 0;
            int lon = 0;

            ArrayFloat.D5 data5D = (ArrayFloat.D5) humidityVar.read();
            for (lon = 0; lon < 11; lon++) {
                for (lat = 0; lat < 10; lat++) {
                    System.out.printf("%.8f  ", data5D.get(time, ens, fhour, lat, lon));
                }
                System.out.println();
            }
            //System.out.println(data5D.get(1,0,1,1,1));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ncFile != null) {
                try {
                    ncFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

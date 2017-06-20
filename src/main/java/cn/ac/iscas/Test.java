package cn.ac.iscas;

import cn.ac.iscas.dao.MeteorologicalInfoDao;
import cn.ac.iscas.dao.MeteorologicalStationDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by admin on 2017/5/23.
 */
public class Test {
    MeteorologicalInfoDao meteorologicalInfoDao;
    MeteorologicalStationDao meteorologicalStationDao;

    public Test() {
        meteorologicalInfoDao = new MeteorologicalInfoDao();
        meteorologicalStationDao = new MeteorologicalStationDao();
    }

    public static void main(String args[]) {
        /*
        List<MeteorologicalStation> meteorologicalStations = new MeteorologicalStationDao().getAll();
        System.out.println(meteorologicalStations);
        for (MeteorologicalStation station: meteorologicalStations) {
            System.out.println(station.getMeteorologicalInfos());
        }
        List<MeteorologicalInfo> meteorologicalInfo = new MeteorologicalInfoDao().getAll();
        System.out.println(meteorologicalInfo);

        MeteorologicalStation station = new MeteorologicalStation();
        station.setId(123456);
        new MeteorologicalStationDao().saveEntity(station);
        */
        // 测试执行dos命令

        System.out.println(System.getProperty("user.dir"));
        String workspace = "D:\\Project\\Soft\\ANUSPLIN软件\\Anuspl43\\bin";
        String command1 = "splina<china_pre.cmd>china_pre.log";
        String command2 = "lapgrd<china_grd.cmd>china_grd.log";
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", command1);
            builder.directory(new File(workspace));
            builder.redirectErrorStream(true);
            Process p = builder.start();
            InputStream is = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            p.waitFor();
            if (p.exitValue() != 0) {
                System.out.println("执行命令失败！");
            }
            builder.command("cmd", "/c", command2);
            p = builder.start();
            p.waitFor();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //测试生成dat
        /*
        Test test = new Test();
        List<MeteorologicalStation> meteorologicalStations = test.meteorologicalStationDao.getAllByAlt();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            java.util.Date udate = format.parse("20020101");
            date = new Date(udate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        DecimalFormat format1 = new DecimalFormat("0.000000");
        DecimalFormat format2 = new DecimalFormat("0.00");
        for(MeteorologicalStation meteorologicalStation : meteorologicalStations){
            MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
            meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
            meteorologicalInfo.setDate(date);
            test.meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
            String code = FileUtil.formatString(6,String.valueOf(meteorologicalStation.getId()));
            String lon = FileUtil.formatString(14,format1.format(meteorologicalStation.getLon()));
            String lat = FileUtil.formatString(14,format1.format(meteorologicalStation.getLat()));
            String alt = FileUtil.formatString(10,format2.format(meteorologicalStation.getAlt()));
            String pre = FileUtil.formatString(10,format2.format(meteorologicalStation.getAlt()));
            sb.append(code+lon+lat+alt+pre).append("\n");

        }
        FileUtil.writeToFile("D:\\Project\\Soft\\ANUSPLIN软件\\Anuspl43\\bin\\a.dat",sb.toString());
        */
    }

}

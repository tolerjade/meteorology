package cn.ac.iscas.controller;

import cn.ac.iscas.dao.MeteorologicalInfoDao;
import cn.ac.iscas.dao.MeteorologicalStationDao;
import cn.ac.iscas.entity.MeteorologicalInfo;
import cn.ac.iscas.entity.MeteorologicalStation;
import cn.ac.iscas.util.DosUtil;
import cn.ac.iscas.util.FileUtil;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by admin on 2017/5/24.
 */
public class MeteorologicalInfoController {
    MeteorologicalInfoDao meteorologicalInfoDao;
    MeteorologicalStationDao meteorologicalStationDao;

    public MeteorologicalInfoController() {
        meteorologicalInfoDao = new MeteorologicalInfoDao();
        meteorologicalStationDao = new MeteorologicalStationDao();

    }

    public static void main(String[] args) {
        MeteorologicalInfoController meteorologicalInfoController = new MeteorologicalInfoController();
        //处理站点数据
        meteorologicalInfoController.interpolateRhu(meteorologicalInfoController);
        //处理温度格点数据
        //meteorologicalInfoController.handle_grid_tem(meteorologicalInfoController);
        //处理降水格点数据
        //meteorologicalInfoController.handle_grid_pre(meteorologicalInfoController);
        /*
        float base_lat = 18;
        float base_lon = 72;
        float lon = (float) 72.12344;
        float lat = (float) 18.4963546454;
        int index_i = (int)((lat-base_lat)/0.5);
        int index_j = (int)((lon-base_lon)/0.5);
        System.out.println(index_i + "  "+ index_j);
        */
        //DosUtil.executeDosCommand("D:\\Project\\Soft\\ANUSPLIN软件\\Anuspl43\\bin","move ..\\*.log .");
    }

    public void handle_pre(String line, List<Integer> list_station, List<MeteorologicalStation> meteorologicalStations) {
        String vars[] = line.split(" +");
        //System.out.println(FileUtil.getDate(vars));
        String pre_8 = vars[7];
        String pre_20 = vars[8];
        String pre_total = vars[9];
        Date date = FileUtil.getDate(vars);
        int station_id = Integer.parseInt(vars[0]);
        if (list_station.contains(station_id)) {
            MeteorologicalStation meteorologicalStation = null;
            for (MeteorologicalStation tmp : meteorologicalStations) {
                if (tmp.getId() == station_id) {
                    meteorologicalStation = tmp;
                    break;
                }
            }
            MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
            meteorologicalInfo.setDate(date);
            meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
            meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
            meteorologicalInfo.setPre_8(pre_8);
            meteorologicalInfo.setPre_20(pre_20);
            meteorologicalInfo.setPre_total(pre_total);
            meteorologicalInfoDao.saveEntity(meteorologicalInfo);
            System.out.println(station_id + " " + pre_8 + " " + pre_20 + " " + pre_total + " " + date + " " + meteorologicalInfo.getMeteorologicalStation());
        }
    }

    public void handle_evp(String line, List<Integer> list_station, List<MeteorologicalStation> meteorologicalStations) {
        String vars[] = line.split(" +");
        //System.out.println(FileUtil.getDate(vars));
        String evp_small = vars[7];
        String evp_large = vars[8];
        Date date = FileUtil.getDate(vars);
        int station_id = Integer.parseInt(vars[0]);
        if (list_station.contains(station_id)) {
            MeteorologicalStation meteorologicalStation = null;
            for (MeteorologicalStation tmp : meteorologicalStations) {
                if (tmp.getId() == station_id) {
                    meteorologicalStation = tmp;
                    break;
                }
            }
            MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
            meteorologicalInfo.setDate(date);
            meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
            meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
            meteorologicalInfo.setEvp_small(evp_small);
            meteorologicalInfo.setEvp_large(evp_large);
            meteorologicalInfoDao.saveEntity(meteorologicalInfo);
            System.out.println(station_id + " " + evp_small + " " + evp_large + "  " + date + " " + meteorologicalInfo.getMeteorologicalStation());
        }
    }

    public void handle_win(String line, List<Integer> list_station, List<MeteorologicalStation> meteorologicalStations) {
        String vars[] = line.split(" +");
        //System.out.println(FileUtil.getDate(vars));
        String win_average_speed = vars[7];
        String win_max_speed = vars[8];
        String win_max_direction = vars[9];
        String win_extrem_speed = vars[10];
        String win_extrem_direction = vars[11];
        Date date = FileUtil.getDate(vars);
        int station_id = Integer.parseInt(vars[0]);
        if (list_station.contains(station_id)) {
            MeteorologicalStation meteorologicalStation = null;
            for (MeteorologicalStation tmp : meteorologicalStations) {
                if (tmp.getId() == station_id) {
                    meteorologicalStation = tmp;
                    break;
                }
            }
            MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
            meteorologicalInfo.setDate(date);
            meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
            meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
            meteorologicalInfo.setWin_average_speed(win_average_speed);
            meteorologicalInfo.setWin_max_speed(win_max_speed);
            meteorologicalInfo.setWin_max_direction(win_max_direction);
            meteorologicalInfo.setWin_extrem_speed(win_extrem_speed);
            meteorologicalInfo.setWin_extrem_direction(win_extrem_direction);
            meteorologicalInfoDao.saveEntity(meteorologicalInfo);
            System.out.println(station_id + " " + win_average_speed + " " + win_max_speed + "  " + win_max_direction + " " + win_extrem_speed + "  " + win_extrem_direction + " " + meteorologicalInfo.getMeteorologicalStation());
        }
    }

    public void handle_prs(String line, List<Integer> list_station, List<MeteorologicalStation> meteorologicalStations) {
        String vars[] = line.split(" +");
        //System.out.println(FileUtil.getDate(vars));
        String prs_average = vars[7];
        String prs_max = vars[8];
        String prs_min = vars[9];
        Date date = FileUtil.getDate(vars);
        int station_id = Integer.parseInt(vars[0]);
        if (list_station.contains(station_id)) {
            MeteorologicalStation meteorologicalStation = null;
            for (MeteorologicalStation tmp : meteorologicalStations) {
                if (tmp.getId() == station_id) {
                    meteorologicalStation = tmp;
                    break;
                }
            }
            MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
            meteorologicalInfo.setDate(date);
            meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
            meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
            meteorologicalInfo.setPrs_average(prs_average);
            meteorologicalInfo.setPrs_max(prs_max);
            meteorologicalInfo.setPrs_min(prs_min);
            meteorologicalInfoDao.saveEntity(meteorologicalInfo);
            System.out.println(station_id + " " + prs_average + " " + prs_max + " " + prs_min + " " + date + " " + meteorologicalInfo.getMeteorologicalStation());
        }
    }

    public void handle_tem(String line, List<Integer> list_station, List<MeteorologicalStation> meteorologicalStations) {
        String vars[] = line.split(" +");
        //System.out.println(FileUtil.getDate(vars));
        String tem_average = vars[7];
        String tem_max = vars[8];
        String tem_min = vars[9];
        Date date = FileUtil.getDate(vars);
        int station_id = Integer.parseInt(vars[0]);
        if (list_station.contains(station_id)) {
            MeteorologicalStation meteorologicalStation = null;
            for (MeteorologicalStation tmp : meteorologicalStations) {
                if (tmp.getId() == station_id) {
                    meteorologicalStation = tmp;
                    break;
                }
            }
            MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
            meteorologicalInfo.setDate(date);
            meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
            meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
            meteorologicalInfo.setTem_average(tem_average);
            meteorologicalInfo.setTem_max(tem_max);
            meteorologicalInfo.setTem_min(tem_min);
            meteorologicalInfoDao.saveEntity(meteorologicalInfo);
            System.out.println(station_id + " " + tem_average + " " + tem_max + " " + tem_min + " " + date + " " + meteorologicalInfo.getMeteorologicalStation());
        }
    }

    public void handle_ssd(String line, List<Integer> list_station, List<MeteorologicalStation> meteorologicalStations) {
        String vars[] = line.split(" +");
        //System.out.println(FileUtil.getDate(vars));
        String ssd = vars[7];
        Date date = FileUtil.getDate(vars);
        int station_id = Integer.parseInt(vars[0]);
        if (list_station.contains(station_id)) {
            MeteorologicalStation meteorologicalStation = null;
            for (MeteorologicalStation tmp : meteorologicalStations) {
                if (tmp.getId() == station_id) {
                    meteorologicalStation = tmp;
                    break;
                }
            }
            MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
            meteorologicalInfo.setDate(date);
            meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
            meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
            meteorologicalInfo.setSsd(ssd);
            meteorologicalInfoDao.saveEntity(meteorologicalInfo);
            System.out.println(station_id + " " + ssd + " " + date + " " + meteorologicalInfo.getMeteorologicalStation());
        }
    }

    public void handle_rhu(String line, List<Integer> list_station, List<MeteorologicalStation> meteorologicalStations) {
        String vars[] = line.split(" +");
        //System.out.println(FileUtil.getDate(vars));
        String rhu_average = vars[7];
        String rhu_min = vars[8];
        Date date = FileUtil.getDate(vars);
        int station_id = Integer.parseInt(vars[0]);
        if (list_station.contains(station_id)) {
            MeteorologicalStation meteorologicalStation = null;
            for (MeteorologicalStation tmp : meteorologicalStations) {
                if (tmp.getId() == station_id) {
                    meteorologicalStation = tmp;
                    break;
                }
            }
            MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
            meteorologicalInfo.setDate(date);
            meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
            meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
            meteorologicalInfo.setRhu_average(rhu_average);
            meteorologicalInfo.setRhu_min(rhu_min);
            meteorologicalInfoDao.saveEntity(meteorologicalInfo);
            System.out.println(station_id + " " + rhu_average + " " + rhu_min + " " + meteorologicalInfo.getMeteorologicalStation());
        }
    }

    public void handle_gst(String line, List<Integer> list_station, List<MeteorologicalStation> meteorologicalStations) {
        String vars[] = line.split(" +");
        //System.out.println(FileUtil.getDate(vars));
        String gst_average = vars[7];
        String gst_max = vars[8];
        String gst_min = vars[9];
        Date date = FileUtil.getDate(vars);
        int station_id = Integer.parseInt(vars[0]);
        if (list_station.contains(station_id)) {
            MeteorologicalStation meteorologicalStation = null;
            for (MeteorologicalStation tmp : meteorologicalStations) {
                if (tmp.getId() == station_id) {
                    meteorologicalStation = tmp;
                    break;
                }
            }
            MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
            meteorologicalInfo.setDate(date);
            meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
            meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
            meteorologicalInfo.setGst_average(gst_average);
            meteorologicalInfo.setGst_max(gst_max);
            meteorologicalInfo.setGst_min(gst_min);
            meteorologicalInfoDao.saveEntity(meteorologicalInfo);
            System.out.println(station_id + " " + gst_average + " " + gst_max + " " + gst_min + " " + date + " " + meteorologicalInfo.getMeteorologicalStation());
        }
    }

    //处理站点数据
    public void handle_stationInfo(MeteorologicalInfoController meteorologicalInfoController) {
        String directory = "D:\\Project\\Stem\\资料\\中国气象数据网\\气象数据\\站点数据2002-2016\\";
        //需要修改，对于不同的气象变量--------------------------------------------1
        List<String> list_filename = FileUtil.generateFileName("SURF_CLI_CHN_MUL_DAY_CES-WIN-11002-");
        List<Integer> list_station = meteorologicalInfoController.meteorologicalStationDao.getAttribute("id");
        List<MeteorologicalStation> meteorologicalStations = meteorologicalInfoController.meteorologicalStationDao.getAllByAlt();
        String[] vars = {"\\pre降水\\", "\\0cm地温\\", "\\EVP蒸发\\", "\\prs气压\\", "\\RHU相对湿度\\", "\\SSD日照时数\\", "\\TEM气温\\", "\\WIN风向风速\\"};
        for (String file : list_filename) {
            StringBuffer file_name = new StringBuffer();
            //需要修改，对于不同的气象变量--------------------------------------------2
            file_name.append(directory).append(vars[7]).append(file);
            List<String> contents = FileUtil.readFileByLines(file_name.toString());
            for (String line : contents) {
                if (line.length() == 0)
                    continue;
                //需要修改，对于不同的气象变量--------------------------------------------3
                meteorologicalInfoController.handle_win(line, list_station, meteorologicalStations);
            }
        }
        System.out.println(list_station);
    }

    //处理格点温度数据
    public void handle_grid_tem(MeteorologicalInfoController meteorologicalInfoController) {
        String directory = "D:\\Project\\Stem\\资料\\中国气象数据网\\气象数据\\中国地面气温日值0.5°×0.5°格点数据集(V2.0)\\Data\\";
        //需要修改，对于不同的气象变量--------------------------------------------1 Max min mean
        List<String> list_filename = FileUtil.generateFileNameDay("SURF_CLI_CHN_TEM_DAY_GRID_0.5-MEAN-");
        List<Integer> list_station = meteorologicalInfoController.meteorologicalStationDao.getAttributeByidLike("id", "53%");
        List<MeteorologicalStation> meteorologicalStations = meteorologicalInfoController.meteorologicalStationDao.getAllByIdLike("53%");
        float base_lat = 18;
        float base_lon = 72;
        for (String file : list_filename) {
            StringBuffer file_name = new StringBuffer();
            file_name.append(directory).append(file);
            //System.out.println(file_name.toString());
            List<String> contents = FileUtil.readFileByLines(file_name.toString());
            String tem_values[][] = new String[72][128];
            int row = 127;
            int col = 71;
            for (String line : contents) {
                //System.out.println(line);
                line = line.trim();
                String vars[] = line.split(" +");
                if (vars.length != 128) {
                    continue;
                }
                //从文件中读出的数据后，存储到二维数组。行上下调换
                for (int i = 0; i <= row; i++) {
                    tem_values[col][i] = vars[i];
                }
                col--;
                //System.out.println(line);
            }
            for (MeteorologicalStation meteorologicalStation : meteorologicalStations) {
                float lat = meteorologicalStation.getLat();
                float lon = meteorologicalStation.getLon();
                int index_i = (int) ((lat - base_lat) / 0.5);
                int index_j = (int) ((lon - base_lon) / 0.5);
                MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
                meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                Date date = null;
                try {
                    java.util.Date udate = format.parse(file.substring(35, 43));
                    date = new Date(udate.getTime());
                    //System.out.println(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                meteorologicalInfo.setDate(date);
                meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
                //---------------------------------------------------------------------------修改数据
                meteorologicalInfo.setTem_average(tem_values[index_i][index_j]);
                meteorologicalInfoDao.saveEntity(meteorologicalInfo);
                //System.out.println( meteorologicalStation.getName() +" "+"[" + index_i +"," + index_j+"] :" + tem_values[index_i][index_j]);
            }
            /*
            for(int i = 0; i < 72; i++){
                System.out.println(i+1 + ":");
                for(int j = 0; j < 128; j++){
                    System.out.print(tem_values[i][j] + " ");
                }
                System.out.println();
            }
            */
        }
    }

    //处理格点降水数据
    public void handle_grid_pre(MeteorologicalInfoController meteorologicalInfoController) {
        String directory = "D:\\Project\\Stem\\资料\\中国气象数据网\\气象数据\\中国地面降水日值0.5°×0.5°格点数据集(V2.0)\\Data\\";
        //需要修改，对于不同的气象变量--------------------------------------------1 Max min mean
        List<String> list_filename = FileUtil.generateFileNameDay("SURF_CLI_CHN_PRE_DAY_GRID_0.5-");
        List<Integer> list_station = meteorologicalInfoController.meteorologicalStationDao.getAttributeByidLike("id", "53%");
        List<MeteorologicalStation> meteorologicalStations = meteorologicalInfoController.meteorologicalStationDao.getAllByIdLike("53%");
        float base_lat = 18;
        float base_lon = 72;
        for (String file : list_filename) {
            StringBuffer file_name = new StringBuffer();
            file_name.append(directory).append(file);
            //System.out.println(file_name.toString());
            List<String> contents = FileUtil.readFileByLines(file_name.toString());
            String tem_values[][] = new String[72][128];
            int row = 127;
            int col = 71;
            for (String line : contents) {
                line = line.trim();
                String vars[] = line.split(" +");
                if (vars.length != 128) {
                    continue;
                }
                //从文件中读出的数据后，存储到二维数组。行上下调换
                for (int i = 0; i <= row; i++) {
                    tem_values[col][i] = vars[i];
                }
                col--;
                //System.out.println(line);
            }
            for (MeteorologicalStation meteorologicalStation : meteorologicalStations) {
                float lat = meteorologicalStation.getLat();
                float lon = meteorologicalStation.getLon();
                int index_i = (int) ((lat - base_lat) / 0.5);
                int index_j = (int) ((lon - base_lon) / 0.5);
                MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
                meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                Date date = null;
                try {
                    java.util.Date udate = format.parse(file.substring(30, 38));
                    date = new Date(udate.getTime());
                    System.out.println(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                meteorologicalInfo.setDate(date);
                meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
                //---------------------------------------------------------------------------修改数据
                if (tem_values[index_i][index_j] != null && tem_values[index_i][index_j].equals("-9999.0"))
                    meteorologicalInfo.setPre_total("-99.0");
                else
                    meteorologicalInfo.setPre_total(tem_values[index_i][index_j]);
                //meteorologicalInfoDao.saveEntity(meteorologicalInfo);
                System.out.println(meteorologicalStation.getName() + " " + "[" + index_i + "," + index_j + "] :" + meteorologicalInfo.getPre_total());
            }
            /*
            for(int i = 0; i < 72; i++){
                System.out.println(i+1 + ":");
                for(int j = 0; j < 128; j++){
                    System.out.print(tem_values[i][j] + " ");
                }
                System.out.println();
            }
            */
        }
    }

    //插值处理降水
    public void interpolatePre(MeteorologicalInfoController meteorologicalInfoController) {
        String directoryPath = "D:\\Project\\interpolate\\Anuspl43\\bin\\PRE_";
        String workspace = "D:\\Project\\interpolate\\Anuspl43\\bin";
        String pythonWorkspace = "C:\\Users\\admin\\Desktop";
        List<MeteorologicalStation> meteorologicalStations = meteorologicalInfoController.meteorologicalStationDao.getAllByAlt();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String file = null;
        try {
            java.util.Date udate = format.parse("20161231");
            Date date = null;
            Calendar calendar = new GregorianCalendar();
            DecimalFormat format1 = new DecimalFormat("0.000000");
            DecimalFormat format2 = new DecimalFormat("0.00");
            for (int i = 0; i < 1826; i++) {
                System.out.println(new java.util.Date().toString());
                StringBuffer sb = new StringBuffer();
                date = new Date(udate.getTime());
                for (MeteorologicalStation meteorologicalStation : meteorologicalStations) {
                    MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
                    meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
                    meteorologicalInfo.setDate(date);
                    meteorologicalInfoController.meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
                    if (meteorologicalInfo.getPre_total() == null)
                        continue;
                    if (meteorologicalInfo.getPre_total().equals("32700"))
                        continue;
                    String code = FileUtil.formatString(6, String.valueOf(meteorologicalStation.getId()));
                    String lon = FileUtil.formatString(14, format1.format(meteorologicalStation.getLon()));
                    String lat = FileUtil.formatString(14, format1.format(meteorologicalStation.getLat()));
                    String alt = FileUtil.formatString(10, format2.format(meteorologicalStation.getAlt()));
                    String pre = FileUtil.formatString(10, format2.format(Float.parseFloat(meteorologicalInfo.getPre_total()) / 10));
                    sb.append(code + lon + lat + alt + pre).append("\n");
                    //System.out.println(meteorologicalInfo);
                }
                file = directoryPath + date.toString() + ".dat";
                String filename = "PRE_" + date.toString();
                String selnotFile = directoryPath + "Selnot_" + date.toString() + ".cmd";
                String selnotLog = directoryPath + "Selnot_" + date.toString() + ".log";
                String splinbFile = directoryPath + "Splinb_" + date.toString() + ".cmd";
                String splinbLog = directoryPath + "Splinb_" + date.toString() + ".log";
                String lapgrdFile = directoryPath + "Lapgrd_" + date.toString() + ".cmd";
                String lapgrdLog = directoryPath + "Lapgrd_" + date.toString() + ".log";
                FileUtil.writeToFile(selnotFile, generatePreSelnot(filename));
                FileUtil.writeToFile(splinbFile, generatePreSplinb(filename));
                FileUtil.writeToFile(lapgrdFile, generatePrelapgrd(filename));
                FileUtil.writeToFile(file, sb.toString());
                String selnotCommand = "selnot<" + selnotFile + ">" + selnotLog;
                DosUtil.executeDosCommand(workspace, selnotCommand);
                String splinbCommand = "splinb<" + splinbFile + ">" + splinbLog;
                DosUtil.executeDosCommand(workspace, splinbCommand);
                String lapgrdCommand = "lapgrd<" + lapgrdFile + ">" + lapgrdLog;
                DosUtil.executeDosCommand(workspace, lapgrdCommand);

                DosUtil.executeDosCommand(workspace, "mkdir out\\pre\\" + date.toString());
                DosUtil.executeDosCommand(workspace, "move PRE* out\\pre\\" + date.toString());
                DosUtil.executeDosCommand(workspace, "move GRD* GRD\\PRE");
                DosUtil.executeDosCommand(workspace, "move COV* GRD\\PRE");
                DosUtil.executeDosCommand(pythonWorkspace, "python interpolatePre.py");
                System.out.println(selnotCommand);
                System.out.println(splinbCommand);
                System.out.println(lapgrdCommand);
                calendar.setTime(udate);
                calendar.add(Calendar.DATE, -1);
                udate = calendar.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //生成降水的lapgrp内容
    public String generatePrelapgrd(String fileName) {
        StringBuffer sb = new StringBuffer();
        sb.append(fileName).append(".sur").append("\n"); // surface file name
        sb.append(1).append("\n"); // surface number
        sb.append(1).append("\n"); // transform surface values
        sb.append(1).append("\n"); // type of surface calculation
        sb.append(fileName).append(".cov").append("\n"); // error covariance file
        sb.append(2).append("\n"); // type of error calculation
        sb.append("\n"); // maximum standard error
        sb.append(1).append("\n"); // position option
        sb.append(1).append("\n"); // index of first grid variable
        sb.append("62.1483 138.8283 0.01").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append(2).append("\n"); // index of second grid variable
        sb.append("13.1320 66.3120 0.01").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append(0).append("\n"); // mode of mask grid
        sb.append(2).append("\n"); // mode of 3rd independent variable
        sb.append("china_dem01.txt").append("\n"); // input grid file name
        sb.append(2).append("\n");// mode of output grids
        sb.append("-99.0").append("\n"); // special value for output grids
        sb.append("GRD_").append(fileName).append(".grd").append("\n"); // name of output grid file for surface 1
        sb.append("\n"); //  output grid format
        sb.append(2).append("\n");// mode of output error grids
        sb.append("-99.0").append("\n"); // special value for output error grids
        sb.append("COV_").append(fileName).append(".grd").append("\n"); // name of output grid file for surface 1
        sb.append("\n"); //  output grid format
        return sb.toString();
    }

    //生成降水的splinb内容
    public String generatePreSplinb(String filename) {
        StringBuffer sb = new StringBuffer();
        sb.append("Daily Rainfall").append("\n"); //title of fitted surface
        sb.append(7).append("\n"); //surface value units code
        sb.append(3).append("\n");// Number of independent spline variables
        sb.append(0).append("\n");// Number of independent covariates
        sb.append(0).append("\n");// Number of surface spline variables
        sb.append(0).append("\n");// Number of surface covariates
        sb.append("62.1483 138.8283 0 5 3.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("13.1320 66.3120 0 5 2.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("0 7000.0 1 1").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("1000.0").append("\n");// transformation coefficient
        sb.append(2).append("\n"); // dependent variable transformation
        sb.append(3).append("\n"); // order of spline
        sb.append(1).append("\n"); // number of surfaces
        sb.append(0).append("\n"); // number of relative variances
        sb.append(1).append("\n"); // optimization directive
        sb.append(1).append("\n"); // smoothing directive
        sb.append(filename).append(".dat").append("\n"); //data file name
        sb.append(200).append("\n"); // maximum number of data points
        sb.append(6).append("\n"); // No. of characters in site label
        sb.append("(a6,2f14.6,f10.2,1f10.2)").append("\n"); // data format
        sb.append(filename).append(".not").append("\n"); // output knot file
        sb.append(100).append("\n"); // maximum number of data knots
        sb.append("\n"); // input bad data file name
        sb.append(filename).append(".flg").append("\n"); // output bad data file name
        sb.append(filename).append(".res").append("\n"); // output large residual file name
        sb.append(filename).append(".opt").append("\n"); // output optimization parameters file name
        sb.append(filename).append(".sur").append("\n"); // output surface coefficients file name
        sb.append(filename).append(".lis").append("\n"); // output data list file name
        sb.append(filename).append(".cov").append("\n"); // output error covariance file name
        sb.append("\n"); // validation data file name
        return sb.toString();
    }

    //生成降水的selnot内容
    public String generatePreSelnot(String filename) {
        StringBuffer sb = new StringBuffer();
        sb.append(3).append("\n");// Number of independent spline variables
        sb.append(0).append("\n");// Number of independent covariates
        sb.append(0).append("\n");// Number of surface spline variables
        sb.append(0).append("\n");// Number of surface covariates
        sb.append("62.1483 138.8283 0 5 3.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("13.1320 66.3120 0 5 2.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("0 7000.0 1 1").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("1000.0").append("\n");// transformation coefficient
        sb.append(2).append("\n"); // dependent variable transformation
        sb.append(1).append("\n"); // number of surfaces
        sb.append(0).append("\n"); // number of error variance
        sb.append(filename).append(".dat").append("\n"); // data file name
        sb.append(200).append("\n"); // maximum number of data points
        sb.append(6).append("\n"); // No. of characters in site label
        sb.append("(a6,2f14.6,f10.2,1f10.2)").append("\n"); // data format
        sb.append(filename).append(".not").append("\n"); // output knot file
        sb.append(filename).append(".rej").append("\n"); // output rejected points file
        sb.append(80).append("\n"); //number of knots
        return sb.toString();
    }

    //插值处理温度
    public void interpolateTem(MeteorologicalInfoController meteorologicalInfoController) {
        String directoryPath = "D:\\Project\\interpolate\\Anuspl43\\bin\\TEM_MAX_";
        String workspace = "D:\\Project\\interpolate\\Anuspl43\\bin";
        String pythonWorkspace = "C:\\Users\\admin\\Desktop";
        List<MeteorologicalStation> meteorologicalStations = meteorologicalInfoController.meteorologicalStationDao.getAllByAlt();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String file = null;
        try {
            java.util.Date udate = format.parse("20160631");
            Date date = null;
            Calendar calendar = new GregorianCalendar();
            DecimalFormat format1 = new DecimalFormat("0.000000");
            DecimalFormat format2 = new DecimalFormat("0.00");
            for (int i = 0; i < 1; i++) {
                System.out.println(new java.util.Date().toString());
                StringBuffer sb = new StringBuffer();
                date = new Date(udate.getTime());
                for (MeteorologicalStation meteorologicalStation : meteorologicalStations) {
                    MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
                    meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
                    meteorologicalInfo.setDate(date);
                    meteorologicalInfoController.meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
                    if (meteorologicalInfo.getTem_max() == null)
                        continue;
                    if (meteorologicalInfo.getTem_max().equals("32766"))
                        continue;
                    String code = FileUtil.formatString(6, String.valueOf(meteorologicalStation.getId()));
                    String lon = FileUtil.formatString(14, format1.format(meteorologicalStation.getLon()));
                    String lat = FileUtil.formatString(14, format1.format(meteorologicalStation.getLat()));
                    String alt = FileUtil.formatString(10, format2.format(meteorologicalStation.getAlt()));
                    String tem = FileUtil.formatString(10, format2.format(Float.parseFloat(meteorologicalInfo.getTem_max()) / 10));
                    sb.append(code + lon + lat + alt + tem).append("\n");
                    //System.out.println(meteorologicalInfo);
                }
                file = directoryPath + date.toString() + ".dat";
                String filename = "TEM_MAX_" + date.toString();
                String selnotFile = directoryPath + "Selnot_" + date.toString() + ".cmd";
                String selnotLog = directoryPath + "Selnot_" + date.toString() + ".log";
                String splinbFile = directoryPath + "Splinb_" + date.toString() + ".cmd";
                String splinbLog = directoryPath + "Splinb_" + date.toString() + ".log";
                String lapgrdFile = directoryPath + "Lapgrd_" + date.toString() + ".cmd";
                String lapgrdLog = directoryPath + "Lapgrd_" + date.toString() + ".log";
                FileUtil.writeToFile(selnotFile, generateTemSelnot(filename));
                FileUtil.writeToFile(splinbFile, generateTemSplinb(filename));
                FileUtil.writeToFile(lapgrdFile, generateTemlapgrd(filename));
                FileUtil.writeToFile(file, sb.toString());
                String selnotCommand = "selnot<" + selnotFile + ">" + selnotLog;
                DosUtil.executeDosCommand(workspace, selnotCommand);
                String splinbCommand = "splinb<" + splinbFile + ">" + splinbLog;
                DosUtil.executeDosCommand(workspace, splinbCommand);
                String lapgrdCommand = "lapgrd<" + lapgrdFile + ">" + lapgrdLog;
                DosUtil.executeDosCommand(workspace, lapgrdCommand);

                DosUtil.executeDosCommand(workspace, "mkdir out\\tem_max\\" + date.toString());
                DosUtil.executeDosCommand(workspace, "move TEM* out\\tem_max\\" + date.toString());
                DosUtil.executeDosCommand(workspace, "move GRD* GRD\\TEM\\MAX");
                //DosUtil.executeDosCommand(workspace, "move COV* GRD\\TEM\\MAX");
                DosUtil.executeDosCommand(pythonWorkspace, "python interpolatePre.py");
                System.out.println(selnotCommand);
                System.out.println(splinbCommand);
                System.out.println(lapgrdCommand);
                calendar.setTime(udate);
                calendar.add(Calendar.DATE, -1);
                udate = calendar.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //生成温度的selnot内容
    public String generateTemSelnot(String filename) {
        StringBuffer sb = new StringBuffer();
        sb.append(2).append("\n");// Number of independent spline variables
        sb.append(1).append("\n");// Number of independent covariates
        sb.append(0).append("\n");// Number of surface spline variables
        sb.append(0).append("\n");// Number of surface covariates
        sb.append("62.1483 138.8283 0 5 3.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("13.1320 66.3120 0 5 2.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("0 7000.0 1 1").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("1000.0").append("\n");// transformation coefficient
        sb.append(2).append("\n"); // dependent variable transformation
        sb.append(1).append("\n"); // number of surfaces
        sb.append(0).append("\n"); // number of error variance
        sb.append(filename).append(".dat").append("\n"); // data file name
        sb.append(200).append("\n"); // maximum number of data points
        sb.append(6).append("\n"); // No. of characters in site label
        sb.append("(a6,2f14.6,f10.2,1f10.2)").append("\n"); // data format
        sb.append(filename).append(".not").append("\n"); // output knot file
        sb.append(filename).append(".rej").append("\n"); // output rejected points file
        sb.append(80).append("\n"); //number of knots
        return sb.toString();
    }

    //生成温度的splinb内容
    public String generateTemSplinb(String filename) {
        StringBuffer sb = new StringBuffer();
        sb.append("Daily Max Temperature").append("\n"); //title of fitted surface
        sb.append(5).append("\n"); //surface value units code
        sb.append(2).append("\n");// Number of independent spline variables
        sb.append(1).append("\n");// Number of independent covariates
        sb.append(0).append("\n");// Number of surface spline variables
        sb.append(0).append("\n");// Number of surface covariates
        sb.append("62.1483 138.8283 0 5 3.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("13.1320 66.3120 0 5 2.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("0 7000.0 1 1").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("1000.0").append("\n");// transformation coefficient
        sb.append(0).append("\n"); // dependent variable transformation
        sb.append(2).append("\n"); // order of spline
        sb.append(1).append("\n"); // number of surfaces
        sb.append(0).append("\n"); // number of relative variances
        sb.append(1).append("\n"); // optimization directive
        sb.append(1).append("\n"); // smoothing directive
        sb.append(filename).append(".dat").append("\n"); //data file name
        sb.append(200).append("\n"); // maximum number of data points
        sb.append(6).append("\n"); // No. of characters in site label
        sb.append("(a6,2f14.6,f10.2,1f10.2)").append("\n"); // data format
        sb.append(filename).append(".not").append("\n"); // output knot file
        sb.append(100).append("\n"); // maximum number of data knots
        sb.append("\n"); // input bad data file name
        sb.append(filename).append(".flg").append("\n"); // output bad data file name
        sb.append(filename).append(".res").append("\n"); // output large residual file name
        sb.append(filename).append(".opt").append("\n"); // output optimization parameters file name
        sb.append(filename).append(".sur").append("\n"); // output surface coefficients file name
        sb.append(filename).append(".lis").append("\n"); // output data list file name
        sb.append(filename).append(".cov").append("\n"); // output error covariance file name
        sb.append("\n"); // validation data file name
        return sb.toString();
    }

    //生成温度的lapgrd内容
    public String generateTemlapgrd(String fileName) {
        StringBuffer sb = new StringBuffer();
        sb.append(fileName).append(".sur").append("\n"); // surface file name
        sb.append(1).append("\n"); // surface number
       // sb.append(1).append("\n"); // transform surface values
        sb.append(1).append("\n"); // type of surface calculation
       // sb.append(fileName).append(".cov").append("\n"); // error covariance file
        sb.append("\n");
        //sb.append(2).append("\n"); // type of error calculation
        //sb.append("\n"); // maximum standard error
        sb.append(1).append("\n"); // position option
        sb.append(1).append("\n"); // index of first grid variable
        sb.append("62.1483 138.8283 0.01").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append(2).append("\n"); // index of second grid variable
        sb.append("13.1320 66.3120 0.01").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append(0).append("\n"); // mode of mask grid
        sb.append(2).append("\n"); // mode of 3rd independent variable
        sb.append("china_dem01.txt").append("\n"); // input grid file name
        sb.append(2).append("\n");// mode of output grids
        sb.append("-99.0").append("\n"); // special value for output grids
        sb.append("GRD_").append(fileName).append(".grd").append("\n"); // name of output grid file for surface 1
        sb.append("\n"); //  output grid format
        //sb.append(2).append("\n");// mode of output error grids
        //sb.append("-99.0").append("\n"); // special value for output error grids
        //sb.append("COV_").append(fileName).append(".grd").append("\n"); // name of output grid file for surface 1
        //sb.append("\n"); //  output grid format
        return sb.toString();
    }

    //插值处理湿度
    public void interpolateRhu(MeteorologicalInfoController meteorologicalInfoController) {
        String directoryPath = "D:\\Project\\interpolate\\Anuspl43\\bin\\RHU_AVG_";
        String workspace = "D:\\Project\\interpolate\\Anuspl43\\bin";
        String pythonWorkspace = "C:\\Users\\admin\\Desktop";
        List<MeteorologicalStation> meteorologicalStations = meteorologicalInfoController.meteorologicalStationDao.getAllByAlt();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String file = null;
        try {
            java.util.Date udate = format.parse("20160631");
            Date date = null;
            Calendar calendar = new GregorianCalendar();
            DecimalFormat format1 = new DecimalFormat("0.000000");
            DecimalFormat format2 = new DecimalFormat("0.00");
            for (int i = 0; i < 1; i++) {
                System.out.println(new java.util.Date().toString());
                StringBuffer sb = new StringBuffer();
                date = new Date(udate.getTime());
                for (MeteorologicalStation meteorologicalStation : meteorologicalStations) {
                    MeteorologicalInfo meteorologicalInfo = new MeteorologicalInfo();
                    meteorologicalInfo.setMeteorologicalStation(meteorologicalStation);
                    meteorologicalInfo.setDate(date);
                    meteorologicalInfoController.meteorologicalInfoDao.getByDateaAndId(meteorologicalInfo);
                    if (meteorologicalInfo.getRhu_average() == null)
                        continue;
                    if (meteorologicalInfo.getRhu_average().equals("32766"))
                        continue;
                    String code = FileUtil.formatString(6, String.valueOf(meteorologicalStation.getId()));
                    String lon = FileUtil.formatString(14, format1.format(meteorologicalStation.getLon()));
                    String lat = FileUtil.formatString(14, format1.format(meteorologicalStation.getLat()));
                    String alt = FileUtil.formatString(10, format2.format(meteorologicalStation.getAlt()));
                    String tem = FileUtil.formatString(10, format2.format(Float.parseFloat(meteorologicalInfo.getTem_max()) / 10));
                    sb.append(code + lon + lat + alt + tem).append("\n");
                    //System.out.println(meteorologicalInfo);
                }
                file = directoryPath + date.toString() + ".dat";
                String filename = "RHU_AVG_" + date.toString();
                String selnotFile = directoryPath + "Selnot_" + date.toString() + ".cmd";
                String selnotLog = directoryPath + "Selnot_" + date.toString() + ".log";
                String splinbFile = directoryPath + "Splinb_" + date.toString() + ".cmd";
                String splinbLog = directoryPath + "Splinb_" + date.toString() + ".log";
                String lapgrdFile = directoryPath + "Lapgrd_" + date.toString() + ".cmd";
                String lapgrdLog = directoryPath + "Lapgrd_" + date.toString() + ".log";
                FileUtil.writeToFile(selnotFile, generateRhuSelnot(filename));
                FileUtil.writeToFile(splinbFile, generateRhuSplinb(filename));
                FileUtil.writeToFile(lapgrdFile, generateRhulapgrd(filename));
                FileUtil.writeToFile(file, sb.toString());
                String selnotCommand = "selnot<" + selnotFile + ">" + selnotLog;
                DosUtil.executeDosCommand(workspace, selnotCommand);
                String splinbCommand = "splinb<" + splinbFile + ">" + splinbLog;
                DosUtil.executeDosCommand(workspace, splinbCommand);
                String lapgrdCommand = "lapgrd<" + lapgrdFile + ">" + lapgrdLog;
                DosUtil.executeDosCommand(workspace, lapgrdCommand);

                DosUtil.executeDosCommand(workspace, "mkdir out\\rhu_avg\\" + date.toString());
                DosUtil.executeDosCommand(workspace, "move RHU* out\\rhu_avg\\" + date.toString());
                DosUtil.executeDosCommand(workspace, "move GRD* GRD\\RHU\\AVG");
                //DosUtil.executeDosCommand(workspace, "move COV* GRD\\TEM\\MAX");
                DosUtil.executeDosCommand(pythonWorkspace, "python interpolatePre.py");
                System.out.println(selnotCommand);
                System.out.println(splinbCommand);
                System.out.println(lapgrdCommand);
                calendar.setTime(udate);
                calendar.add(Calendar.DATE, -1);
                udate = calendar.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //生成湿度的selnot内容
    public String generateRhuSelnot(String filename) {
        StringBuffer sb = new StringBuffer();
        sb.append(2).append("\n");// Number of independent spline variables
        sb.append(1).append("\n");// Number of independent covariates
        sb.append(0).append("\n");// Number of surface spline variables
        sb.append(0).append("\n");// Number of surface covariates
        sb.append("62.1483 138.8283 0 5 3.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("13.1320 66.3120 0 5 2.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("0 7000.0 1 1").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("1000.0").append("\n");// transformation coefficient
        sb.append(2).append("\n"); // dependent variable transformation
        sb.append(1).append("\n"); // number of surfaces
        sb.append(0).append("\n"); // number of error variance
        sb.append(filename).append(".dat").append("\n"); // data file name
        sb.append(200).append("\n"); // maximum number of data points
        sb.append(6).append("\n"); // No. of characters in site label
        sb.append("(a6,2f14.6,f10.2,1f10.2)").append("\n"); // data format
        sb.append(filename).append(".not").append("\n"); // output knot file
        sb.append(filename).append(".rej").append("\n"); // output rejected points file
        sb.append(80).append("\n"); //number of knots
        return sb.toString();
    }

    //生成湿度的splinb内容
    public String generateRhuSplinb(String filename) {
        StringBuffer sb = new StringBuffer();
        sb.append("Daily Average Relative Humidity").append("\n"); //title of fitted surface
        sb.append(0).append("\n"); //surface value units code
        sb.append(2).append("\n");// Number of independent spline variables
        sb.append(1).append("\n");// Number of independent covariates
        sb.append(0).append("\n");// Number of surface spline variables
        sb.append(0).append("\n");// Number of surface covariates
        sb.append("62.1483 138.8283 0 5 3.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("13.1320 66.3120 0 5 2.0").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("0 7000.0 1 1").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append("1000.0").append("\n");// transformation coefficient
        sb.append(0).append("\n"); // dependent variable transformation
        sb.append(2).append("\n"); // order of spline
        sb.append(1).append("\n"); // number of surfaces
        sb.append(0).append("\n"); // number of relative variances
        sb.append(1).append("\n"); // optimization directive
        sb.append(1).append("\n"); // smoothing directive
        sb.append(filename).append(".dat").append("\n"); //data file name
        sb.append(200).append("\n"); // maximum number of data points
        sb.append(6).append("\n"); // No. of characters in site label
        sb.append("(a6,2f14.6,f10.2,1f10.2)").append("\n"); // data format
        sb.append(filename).append(".not").append("\n"); // output knot file
        sb.append(100).append("\n"); // maximum number of data knots
        sb.append("\n"); // input bad data file name
        sb.append(filename).append(".flg").append("\n"); // output bad data file name
        sb.append(filename).append(".res").append("\n"); // output large residual file name
        sb.append(filename).append(".opt").append("\n"); // output optimization parameters file name
        sb.append(filename).append(".sur").append("\n"); // output surface coefficients file name
        sb.append(filename).append(".lis").append("\n"); // output data list file name
        sb.append(filename).append(".cov").append("\n"); // output error covariance file name
        sb.append("\n"); // validation data file name
        return sb.toString();
    }

    //生成湿度的lapgrd内容
    public String generateRhulapgrd(String fileName) {
        StringBuffer sb = new StringBuffer();
        sb.append(fileName).append(".sur").append("\n"); // surface file name
        sb.append(1).append("\n"); // surface number
       // sb.append(1).append("\n"); // transform surface values
        sb.append(1).append("\n"); // type of surface calculation
       // sb.append(fileName).append(".cov").append("\n"); // error covariance file
        sb.append("\n");
        //sb.append(2).append("\n"); // type of error calculation
        //sb.append("\n"); // maximum standard error
        sb.append(1).append("\n"); // position option
        sb.append(1).append("\n"); // index of first grid variable
        sb.append("62.1483 138.8283 0.01").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append(2).append("\n"); // index of second grid variable
        sb.append("13.1320 66.3120 0.01").append("\n");// lower & upper limits, transf code, ref unit, margins
        sb.append(0).append("\n"); // mode of mask grid
        sb.append(2).append("\n"); // mode of 3rd independent variable
        sb.append("china_dem01.txt").append("\n"); // input grid file name
        sb.append(2).append("\n");// mode of output grids
        sb.append("-99.0").append("\n"); // special value for output grids
        sb.append("GRD_").append(fileName).append(".grd").append("\n"); // name of output grid file for surface 1
        sb.append("\n"); //  output grid format
        //sb.append(2).append("\n");// mode of output error grids
        //sb.append("-99.0").append("\n"); // special value for output error grids
        //sb.append("COV_").append(fileName).append(".grd").append("\n"); // name of output grid file for surface 1
        //sb.append("\n"); //  output grid format
        return sb.toString();
    }
}

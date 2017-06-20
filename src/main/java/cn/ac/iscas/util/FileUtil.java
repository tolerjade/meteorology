package cn.ac.iscas.util;


import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/5/24.
 */
public class FileUtil {
    public static List<String> readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> lists = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                lists.add(tmp);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return lists;
        }
    }

    public static Date getDate(String[] vars) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            String dateString = vars[4] + "-" + vars[5] + "-" + vars[6];
            java.util.Date uDate = format.parse(dateString);
            date = new Date(uDate.getTime());
        } catch (Exception e) {
            System.err.println(vars.length);
            e.printStackTrace();
        }
        return date;
    }

    public static List<String> generateFileName(String prefix) {
        StringBuffer stringBuffer = null;
        List<String> list = new ArrayList<String>();
        for (int i = 2002; i <= 2016; i++) {
            for (int j = 1; j <= 12; j++) {
                stringBuffer = new StringBuffer();
                if (j < 10) {
                    stringBuffer.append(prefix).append(i).append(0).append(j).append(".txt");
                } else {
                    stringBuffer.append(prefix).append(i).append(j).append(".txt");
                }
                list.add(stringBuffer.toString());
            }
        }
        //list.clear();
        //list.add("SURF_CLI_CHN_MUL_DAY_CES-PRE-13011-201212.txt");
        return list;
    }

    public static List<String> generateFileNameDay(String prefix) {
        StringBuffer stringBuffer = null;
        List<String> list = new ArrayList<String>();
        Set<Integer> day31 = new HashSet<Integer>() {{
            add(1);
            add(3);
            add(5);
            add(7);
            add(8);
            add(10);
            add(12);
        }};
        Set<Integer> day30 = new HashSet<Integer>() {{
            add(4);
            add(6);
            add(9);
            add(11);
        }};
        String[] vars = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
                "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        for (int i = 2014; i <= 2016; i++) {
            for (int j = 1; j <= 12; j++) {
                if (day31.contains(j)) {
                    for (int z = 1; z <= 31; z++) {
                        stringBuffer = new StringBuffer();
                        stringBuffer.append(prefix).append(i).append(vars[j]).append(vars[z]).append(".txt");
                        list.add(stringBuffer.toString());
                    }
                } else if (day30.contains(j)) {
                    for (int z = 1; z <= 30; z++) {
                        stringBuffer = new StringBuffer();
                        stringBuffer.append(prefix).append(i).append(vars[j]).append(vars[z]).append(".txt");
                        list.add(stringBuffer.toString());
                    }
                } else {
                    if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                        for (int z = 1; z <= 29; z++) {
                            stringBuffer = new StringBuffer();
                            stringBuffer.append(prefix).append(i).append(vars[j]).append(vars[z]).append(".txt");
                            list.add(stringBuffer.toString());
                        }
                    } else {
                        for (int z = 1; z <= 28; z++) {
                            stringBuffer = new StringBuffer();
                            stringBuffer.append(prefix).append(i).append(vars[j]).append(vars[z]).append(".txt");
                            list.add(stringBuffer.toString());
                        }
                    }
                }
            }
        }
        list.clear();
        list.add("SURF_CLI_CHN_PRE_DAY_GRID_0.5-20020102.txt");
        //list.add("SURF_CLI_CHN_TEM_DAY_GRID_0.5-MAX-20020102.txt");
        return list;
    }

    public static void writeToFile(String fileName, String contents) {
        FileOutputStream fileOutputStream = null;
        File file;
        try {
            file = new File(fileName);
            fileOutputStream = new FileOutputStream(file);
            if (!file.exists())
                file.createNewFile();
            byte[] contentBytes = contents.getBytes();
            fileOutputStream.write(contentBytes);
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String formatString(int l, String s) {
        StringBuffer stringBuffer = new StringBuffer();
        if (s.length() > l)
            stringBuffer.append(s);
        else {
            for (int i = 0; i < l - s.length(); i++) {
                stringBuffer.append(" ");
            }
            stringBuffer.append(s);
        }
        return stringBuffer.toString();
    }
}

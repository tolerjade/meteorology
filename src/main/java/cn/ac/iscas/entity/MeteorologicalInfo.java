package cn.ac.iscas.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by admin on 2017/5/23.
 */
public class MeteorologicalInfo implements Serializable {
    public MeteorologicalStation meteorologicalStation; //气象站信息

    //降水
    private String pre_8; //8时降水
    private String pre_20; //20时降水
    private String pre_total; //累计降水

    //蒸发
    private String evp_large; //大型蒸发
    private String evp_small;  //小型蒸发

    //风速风向
    private String win_max_speed;//最大风速
    private String win_max_direction;//最大风速的风向
    private String win_extrem_speed;//极大风速
    private String win_extrem_direction;//极大风速风向
    private String win_average_speed;//平均风速

    //气压
    private String prs_average;//平均气压
    private String prs_min;//最低气压
    private String prs_max;//最高气压

    //温度
    private String tem_average;//平均温度
    private String tem_max;//最高温度
    private String tem_min;//最低温度

    //日照
    private String ssd;

    //相对湿度
    private String rhu_min;//最小相对湿度
    private String rhu_average;//平均相对湿度

    //0cm地温
    private String gst_average;//平均地表温度
    private String gst_max;//日最高
    private String gst_min;//日最低

    //日期
    private Date date;

    //比湿
    private String noaa_spfh;

    public String getPre_8() {
        return pre_8;
    }

    public void setPre_8(String pre_8) {
        this.pre_8 = pre_8;
    }

    public String getPre_20() {
        return pre_20;
    }

    public void setPre_20(String pre_20) {
        this.pre_20 = pre_20;
    }

    public String getPre_total() {
        return pre_total;
    }

    public void setPre_total(String total) {
        this.pre_total = total;
    }

    public String getEvp_large() {
        return evp_large;
    }

    public void setEvp_large(String evp_large) {
        this.evp_large = evp_large;
    }

    public String getEvp_small() {
        return evp_small;
    }

    public void setEvp_small(String evp_small) {
        this.evp_small = evp_small;
    }

    public String getWin_max_speed() {
        return win_max_speed;
    }

    public void setWin_max_speed(String win_max_speed) {
        this.win_max_speed = win_max_speed;
    }

    public String getWin_max_direction() {
        return win_max_direction;
    }

    public void setWin_max_direction(String win_max_direction) {
        this.win_max_direction = win_max_direction;
    }

    public String getWin_extrem_speed() {
        return win_extrem_speed;
    }

    public void setWin_extrem_speed(String win_extrem_speed) {
        this.win_extrem_speed = win_extrem_speed;
    }

    public String getWin_extrem_direction() {
        return win_extrem_direction;
    }

    public void setWin_extrem_direction(String win_extrem_direction) {
        this.win_extrem_direction = win_extrem_direction;
    }

    public String getWin_average_speed() {
        return win_average_speed;
    }

    public void setWin_average_speed(String win_average_speed) {
        this.win_average_speed = win_average_speed;
    }

    public String getPrs_average() {
        return prs_average;
    }

    public void setPrs_average(String prs_average) {
        this.prs_average = prs_average;
    }

    public String getPrs_min() {
        return prs_min;
    }

    public void setPrs_min(String prs_min) {
        this.prs_min = prs_min;
    }

    public String getPrs_max() {
        return prs_max;
    }

    public void setPrs_max(String prs_max) {
        this.prs_max = prs_max;
    }

    public String getTem_average() {
        return tem_average;
    }

    public void setTem_average(String tem_average) {
        this.tem_average = tem_average;
    }

    public String getTem_max() {
        return tem_max;
    }

    public void setTem_max(String tem_max) {
        this.tem_max = tem_max;
    }

    public String getTem_min() {
        return tem_min;
    }

    public void setTem_min(String tem_min) {
        this.tem_min = tem_min;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getRhu_min() {
        return rhu_min;
    }

    public void setRhu_min(String rhu_min) {
        this.rhu_min = rhu_min;
    }

    public String getRhu_average() {
        return rhu_average;
    }

    public void setRhu_average(String rhu_average) {
        this.rhu_average = rhu_average;
    }

    public String getGst_average() {
        return gst_average;
    }

    public void setGst_average(String gst_average) {
        this.gst_average = gst_average;
    }

    public String getGst_max() {
        return gst_max;
    }

    public void setGst_max(String gst_max) {
        this.gst_max = gst_max;
    }

    public String getGst_min() {
        return gst_min;
    }

    public void setGst_min(String gst_min) {
        this.gst_min = gst_min;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNoaa_spfh() {
        return noaa_spfh;
    }

    public void setNoaa_spfh(String spfh) {
        this.noaa_spfh = spfh;
    }

    public MeteorologicalStation getMeteorologicalStation() {
        return meteorologicalStation;
    }

    public void setMeteorologicalStation(MeteorologicalStation meteorologicalStation) {
        this.meteorologicalStation = meteorologicalStation;
    }

    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof MeteorologicalInfo))
            return false;
        else {
            MeteorologicalInfo meteorologicalInfo = (MeteorologicalInfo) obj;
            if (this.getMeteorologicalStation() != null && meteorologicalInfo.getMeteorologicalStation() != null) {
                if (this.getMeteorologicalStation().getId() != meteorologicalInfo.getMeteorologicalStation().getId())
                    return false;
            } else
                return false;


            if (this.getDate() != null && meteorologicalInfo.getDate() != null) {
                if (this.getDate() != meteorologicalInfo.getDate())
                    return false;
            } else
                return false;
        }
        return true;
    }


    public String toString() {
        return "MeteorologicalInfo: " + this.getPre_total();
    }

}

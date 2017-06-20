package cn.ac.iscas.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2017/5/23.
 */
public class MeteorologicalStation {
    public Set<MeteorologicalInfo> meteorologicalInfos;
    private int id;  //台站号
    private String name; //台站名
    private Float lon; //经度
    private Float lat; //纬度
    private Float alt; //海拔

    public MeteorologicalStation() {
        meteorologicalInfos = new HashSet<MeteorologicalInfo>();
    }

    public Set<MeteorologicalInfo> getMeteorologicalInfos() {
        return meteorologicalInfos;
    }

    public void setMeteorologicalInfos(Set<MeteorologicalInfo> meteorologicalInfos) {
        this.meteorologicalInfos = meteorologicalInfos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getAlt() {
        return alt;
    }

    public void setAlt(Float alt) {
        this.alt = alt;
    }

    public String toString() {
        return "MeteorologicalStation: StationID:" + id + ", Name: " + name + ", lon:" + lon + ", lat:" + lat
                + ", alt:" + alt + "\n";
    }
}

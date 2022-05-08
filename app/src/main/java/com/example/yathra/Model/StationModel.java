package com.example.yathra.Model;

import java.io.Serializable;

public class StationModel implements Serializable {

    int stationID;
    String stationName;
    String masterName;
    String city;

    public StationModel(int stationID, String stationName, String masterName, String city) {
        this.stationID = stationID;
        this.stationName = stationName;
        this.masterName = masterName;
        this.city = city;
    }

    public StationModel(String stationName, String masterName, String city) {
        this.stationName = stationName;
        this.masterName = masterName;
        this.city = city;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

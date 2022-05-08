package com.example.yathra.Model;

import java.io.Serializable;

public class TimetableModel implements Serializable {

    private int id;
    private String rName;
    private String date;
    private String aTime;
    private String dTime;
    private String tName;


    public TimetableModel(int id, String rName, String date, String aTime, String dTime, String tName) {
        this.id = id;
        this.rName = rName;
        this.date = date;
        this.aTime = aTime;
        this.dTime = dTime;
        this.tName = tName;
    }

    public TimetableModel(String rName, String date, String aTime, String dTime, String tName) {
        this.rName = rName;
        this.date = date;
        this.aTime = aTime;
        this.dTime = dTime;
        this.tName = tName;
    }


    //setters & getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}

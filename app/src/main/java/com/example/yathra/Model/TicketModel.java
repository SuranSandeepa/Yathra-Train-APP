package com.example.yathra.Model;

import java.io.Serializable;

public class TicketModel implements Serializable {

    int id;
    String date;
    String time;
    String classType;
    String from;
    String to;
    String price;

    public TicketModel(int id, String date, String time, String classType, String from, String to, String price) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.classType = classType;
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public TicketModel(String date, String time, String classType, String from, String to, String price) {
        this.date = date;
        this.time = time;
        this.classType = classType;
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

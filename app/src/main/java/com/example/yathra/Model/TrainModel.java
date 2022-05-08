package com.example.yathra.Model;

import java.io.Serializable;

public class TrainModel implements Serializable {

    int id;
    String trainName;
    String noOfPassengers;
    String trainCategory;

    public TrainModel(int id, String trainName, String noOfPassengers, String trainCategory) {
        this.id = id;
        this.trainName = trainName;
        this.noOfPassengers = noOfPassengers;
        this.trainCategory = trainCategory;
    }

    public TrainModel(String trainName, String noOfPassengers, String trainCategory) {
        this.trainName = trainName;
        this.noOfPassengers = noOfPassengers;
        this.trainCategory = trainCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(String noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public String getTrainCategory() {
        return trainCategory;
    }

    public void setTrainCategory(String trainCategory) {
        this.trainCategory = trainCategory;
    }
}

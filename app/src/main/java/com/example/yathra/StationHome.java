package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StationHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_home);


    }

    public void displayStationsPage(View view) {
        startActivity(new Intent(StationHome.this, DisplayStation.class));
    }

    public void displayAddStationPage(View view) {
        startActivity(new Intent(StationHome.this, AddStation.class));
    }
}
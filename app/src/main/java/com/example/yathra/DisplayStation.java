package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.yathra.Adapters.StationAdapter;
import com.example.yathra.Adapters.TrainAdapter;
import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.StationModel;
import com.example.yathra.Model.TrainModel;

import java.util.ArrayList;

public class DisplayStation extends AppCompatActivity {

    ArrayList<StationModel> stations;
    YathraDatabaseHelper DBHelper;
    RecyclerView recyclerView;
    TextView totalStations;
    StationAdapter stationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_station);

        totalStations = findViewById(R.id.stationTableTotalST);
        recyclerView = findViewById(R.id.recycleViewDS);

        DBHelper = new YathraDatabaseHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        stations = DBHelper.getAllStationDetails();
        totalStations.setText("Total Stations : " + stations.size());

        stationAdapter = new StationAdapter(stations, this);
        recyclerView.setAdapter(stationAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
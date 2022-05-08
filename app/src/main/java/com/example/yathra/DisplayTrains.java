package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.yathra.Adapters.TrainAdapter;
import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.TrainModel;

import java.util.ArrayList;

public class DisplayTrains extends AppCompatActivity {

    YathraDatabaseHelper DBHelper;
    RecyclerView recyclerView;
    TextView totalTrains;
    ArrayList<TrainModel> trainsDetails;
    TrainAdapter trainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trains);

        totalTrains = findViewById(R.id.tvTotalTrains);
        recyclerView = findViewById(R.id.recycleViewDTT);

        DBHelper = new YathraDatabaseHelper(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        trainsDetails = DBHelper.getAllTrainDetails();

        totalTrains.setText("Total Trains : " + trainsDetails.size());

        trainAdapter = new TrainAdapter(trainsDetails, this);
        recyclerView.setAdapter(trainAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
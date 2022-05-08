package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.StationModel;

public class AddStation extends AppCompatActivity {

    EditText stationName, stationMaster, stationCity;
    Toast dataSavedToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);

        stationName = findViewById(R.id.editTextStationNameAS);
        stationMaster = findViewById(R.id.editTextStationMasterAS);
        stationCity = findViewById(R.id.editTextStationCityAS);

        //Custom Toast Message For Data Saved
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.dataSavedToast));

        dataSavedToast = new Toast(getApplicationContext());
        dataSavedToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        dataSavedToast.setDuration(Toast.LENGTH_SHORT);
        dataSavedToast.setView(layout);

    }

    public void insertStation(View view) {

        String stationNameST = stationName.getText().toString().trim();
        String masterNameST = stationMaster.getText().toString().trim();
        String cityNameST = stationCity.getText().toString().trim();

        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(AddStation.this);

        StationModel SM = new StationModel(stationNameST, masterNameST, cityNameST);

        long result = DBHelper.insertStation(SM);
        if(result > 0){
            //Toast.makeText(this, "Station Saved", Toast.LENGTH_SHORT).show();
            dataSavedToast.show();
        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }


    }

//    public void showStations(View view) {
//
//        startActivity(new Intent(AddStation.this, DisplayStation.class));
//    }

}
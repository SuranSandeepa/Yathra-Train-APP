package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.StationModel;
import com.example.yathra.Model.TrainModel;

public class UpdateStation extends AppCompatActivity {

    EditText stationName, stationMaster, stationCity;
    int id;
    Toast dataUpdateToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_station);

        StationModel SM = (StationModel) getIntent().getExtras().getSerializable("StationModel");

        stationName = findViewById(R.id.editTextStationNameAS);
        stationMaster = findViewById(R.id.editTextStationMasterAS);
        stationCity = findViewById(R.id.editTextStationCityAS);
        id = SM.getStationID();

        stationName.setText(SM.getStationName());
        stationMaster.setText(SM.getMasterName());
        stationCity.setText(SM.getCity());

        //Custom Toast Message For Update Data
        LayoutInflater inflater2 = getLayoutInflater();
        View layout2 = inflater2.inflate(R.layout.custom_toast_update, (ViewGroup)findViewById(R.id.dataUpdateToast));

        dataUpdateToast = new Toast(getApplicationContext());
        dataUpdateToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        dataUpdateToast.setDuration(Toast.LENGTH_SHORT);
        dataUpdateToast.setView(layout2);
    }

    public void updateStation(View view) {


        String sName = stationName.getText().toString().trim();
        String mName = stationMaster.getText().toString().trim();
        String cName = stationCity.getText().toString().trim();

        StationModel SM = new StationModel(id, sName, mName, cName);

        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(this);

        int result = DBHelper.updateStationTable(SM);

        if(result > 0){
            //Toast.makeText(this, "Station Updated", Toast.LENGTH_SHORT).show();
            dataUpdateToast.show();
            finish();
        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
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
import com.example.yathra.Model.TrainModel;

import java.util.ArrayList;

public class AddTrain extends AppCompatActivity {

    EditText edTrainName, edNoPassengers, edTrainC;
    Toast dataSavedToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_train);

        edTrainName = findViewById(R.id.editTextTrainNameAT);
        edNoPassengers = findViewById(R.id.editTextNoOfPassengersAT);
        edTrainC = findViewById(R.id.editTextTrainCategoryAT);


        //Custom Toast Message For Data Saved
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.dataSavedToast));

        dataSavedToast = new Toast(getApplicationContext());
        dataSavedToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        dataSavedToast.setDuration(Toast.LENGTH_SHORT);
        dataSavedToast.setView(layout);

    }

    public void insertTrain(View view) {

        String nameTT = edTrainName.getText().toString().trim();
        String passengersTT = edNoPassengers.getText().toString().trim();
        String categoryTT = edTrainC.getText().toString().trim();

        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(AddTrain.this);

        TrainModel trainM = new TrainModel(nameTT, passengersTT, categoryTT);

        long result = DBHelper.InsertTrain(trainM);
        if(result > 0){
            //Toast.makeText(this, "Train Saved", Toast.LENGTH_SHORT).show();
            dataSavedToast.show();
        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }


//    public void showTrains(View view) {
//
//        startActivity(new Intent(AddTrain.this, DisplayTrains.class));
//
//    }
}
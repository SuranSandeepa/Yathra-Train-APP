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
import com.example.yathra.Model.TimetableModel;
import com.example.yathra.Model.TrainModel;

public class UpdateTrain extends AppCompatActivity {

    EditText edTrainName, edNoPassengers, edTrainC;
    int trainID;
    Toast dataUpdateToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_train);

        TrainModel trainM = (TrainModel) getIntent().getExtras().getSerializable("TrainModel");

        edTrainName = findViewById(R.id.editTextTrainNameUAT);
        edNoPassengers = findViewById(R.id.editTextNoOfPassengersUAT);
        edTrainC = findViewById(R.id.editTextTrainCategoryUAT);
        trainID = trainM.getId();

        edTrainName.setText(trainM.getTrainName());
        edNoPassengers.setText(trainM.getNoOfPassengers());
        edTrainC.setText(trainM.getTrainCategory());


        //Custom Toast Message For Update Data
        LayoutInflater inflater2 = getLayoutInflater();
        View layout2 = inflater2.inflate(R.layout.custom_toast_update, (ViewGroup)findViewById(R.id.dataUpdateToast));

        dataUpdateToast = new Toast(getApplicationContext());
        dataUpdateToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        dataUpdateToast.setDuration(Toast.LENGTH_SHORT);
        dataUpdateToast.setView(layout2);
    }

    public void updateTrain(View view) {

        String nameTT = edTrainName.getText().toString().trim();
        String passengersTT = edNoPassengers.getText().toString().trim();
        String categoryTT = edTrainC.getText().toString().trim();

        TrainModel trainM = new TrainModel(trainID, nameTT, passengersTT, categoryTT);

        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(this);

        int result = DBHelper.updateTrainTable(trainM);

        if(result > 0){
            //Toast.makeText(this, "Train Updated", Toast.LENGTH_SHORT).show();
            dataUpdateToast.show();
            finish();
        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
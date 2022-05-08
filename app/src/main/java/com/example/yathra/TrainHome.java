package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrainHome extends AppCompatActivity {

    Button addTrain, TrainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_home);

        addTrain = findViewById(R.id.btnAddtrainTH);
        TrainList = findViewById(R.id.btnTrainListTH);

        addTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_1 = new Intent(TrainHome.this, AddTrain.class);
                startActivity(intent_1);
            }
        });

        TrainList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_2 = new Intent(TrainHome.this, DisplayTrains.class);
                startActivity(intent_2);
            }
        });
    }
}
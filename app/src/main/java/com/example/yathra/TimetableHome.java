package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimetableHome extends AppCompatActivity {

    Button btnCreateTimetable, btnTimetablesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_home);

        btnCreateTimetable = findViewById(R.id.btnCreateTimetableTH);
        btnTimetablesView = findViewById(R.id.btnTimetablesTH);

        btnCreateTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_1 = new Intent(TimetableHome.this, CreateTimetable.class);
                startActivity(intent_1);
            }
        });

        btnTimetablesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_2 = new Intent(TimetableHome.this, DisplayTimetables.class);
                startActivity(intent_2);
            }
        });
    }
}
package com.example.yathra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yathra.Adapters.TimetableAdapter;
import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.TimetableModel;

import java.util.ArrayList;

public class DisplayTimetables extends AppCompatActivity {

    ArrayList<TimetableModel> Timetables;
    RecyclerView recyclerView;
    TextView timeTableTotalDT;
    TimetableAdapter timetableAdapter;
    YathraDatabaseHelper DBHelper;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_timetables);

        DBHelper = new YathraDatabaseHelper(this);

        recyclerView = findViewById(R.id.recycleView);
        timeTableTotalDT = findViewById(R.id.timeTableTotalDT);

    }

    @Override
    protected void onStart() {

        super.onStart();
        Timetables = DBHelper.getAllTimetables();

        timeTableTotalDT.setText("Total Timetables : " + Timetables.size());//get total timetable size

        timetableAdapter = new TimetableAdapter(Timetables, this);
        recyclerView.setAdapter(timetableAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }
}
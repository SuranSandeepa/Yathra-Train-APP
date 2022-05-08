package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.TimetableModel;

import java.util.Calendar;
import java.util.Locale;

public class CreateTimetable extends AppCompatActivity {

    //Initialize Variables
    YathraDatabaseHelper DBHelper;
    EditText routeName, trainDate, arriveTime, departTime, trainName;
    Button btnCalendar;
    int y, m, d; //For Calender
    int t1Hour, t1Minute, t2Hour, t2Minute;
    Toast dataSavedToast, dataDeleteToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_timetable);

        //Assign Variables
        routeName = findViewById(R.id.editTextRoutNameCT);
        trainDate = findViewById(R.id.editTextDateCT);
        arriveTime = findViewById(R.id.editTextArriveTimeCT);
        departTime = findViewById(R.id.editTextDepartTimeCT);
        trainName = findViewById(R.id.editTextTrainNameCT);

        final Calendar c = Calendar.getInstance();

        //Calendar View
        trainDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                y = c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DATE);

                DatePickerDialog DPD = new DatePickerDialog(CreateTimetable.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        trainDate.setText(i + " /" + i1 + "/" + i2);
                    }
                },y, m, d);
                DPD.show();
            }
        });

        //Custom Toast Message For Data Saved
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.dataSavedToast));

        //        ImageView imageToast = layout.findViewById(R.id.ivToast);
        //        imageToast.setImageResource(R.drawable.toast_train3);
        //        TextView textToast = layout.findViewById(R.id.tvToast);
        //        textToast.setText("Text...");

        dataSavedToast = new Toast(getApplicationContext());
        dataSavedToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        dataSavedToast.setDuration(Toast.LENGTH_SHORT);
        dataSavedToast.setView(layout);

    }

    public void insertTimetable(View view) {

        String name = routeName.getText().toString().trim();
        String date = trainDate.getText().toString().trim();
        String aTime = arriveTime.getText().toString().trim();
        String dTime = departTime.getText().toString().trim();
        String tName = trainName.getText().toString().trim();

        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(CreateTimetable.this);

        TimetableModel tm= new TimetableModel(name, date, aTime, dTime, tName);

        long result = DBHelper.insertTimetable(tm);
        if(result > 0){
            //Toast.makeText(this, "Timetable Saved", Toast.LENGTH_SHORT).show();
            dataSavedToast.show();

        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    //Arrive Time Clock
    public void popTimePicker(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                t1Hour = selectedHour;
                t1Minute = selectedMinute;

                arriveTime.setText(String.format(Locale.getDefault(), "%02d:%02d", t1Hour,t1Minute));

            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, t1Hour, t1Minute, true);

        timePickerDialog.setTitle("Select Arrive Time");
        timePickerDialog.show();
    }

    //Depart Time Clock
    public void popTimePickerDepart(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                t2Hour = selectedHour;
                t2Minute = selectedMinute;

                departTime.setText(String.format(Locale.getDefault(), "%02d:%02d", t2Hour,t2Minute));

            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, t2Hour, t2Minute, true);

        timePickerDialog.setTitle("Select Depart Time");
        timePickerDialog.show();
    }

//    public void showTimetables(View view) {
//
//        startActivity(new Intent(CreateTimetable.this, DisplayTimetables.class));
//    }
}
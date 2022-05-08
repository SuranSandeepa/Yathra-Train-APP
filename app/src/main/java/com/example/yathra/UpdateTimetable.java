package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.TimetableModel;

import java.util.Calendar;
import java.util.Locale;

public class UpdateTimetable extends AppCompatActivity {

    EditText routeName, trainDate, arriveTime, departTime, trainName;
    int id;
    int y, m, d; //For Calender
    int t1Hour, t1Minute, t2Hour, t2Minute;
    Toast dataUpdateToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_timetable);

        TimetableModel tm = (TimetableModel) getIntent().getExtras().getSerializable("TimetableModel");

        id = tm.getId();
        routeName = findViewById(R.id.editTextRoutNameUT);
        trainDate = findViewById(R.id.editTextDateUT);
        arriveTime = findViewById(R.id.editTextArriveTimeUT);
        departTime = findViewById(R.id.editTextDepartTimeUT);
        trainName = findViewById(R.id.editTextTrainNameUT);

        final Calendar c = Calendar.getInstance();

        //Calendar View
        trainDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                y = c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DATE);

                DatePickerDialog DPD = new DatePickerDialog(UpdateTimetable.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        trainDate.setText(i + " /" + i1 + "/" + i2);
                    }
                },y, m, d);
                DPD.show();
            }
        });

        routeName.setText(tm.getrName());
        trainDate.setText(tm.getDate());
        arriveTime.setText(tm.getaTime());
        departTime.setText(tm.getdTime());
        trainName.setText(tm.gettName());

        //Custom Toast Message For Update Data
        LayoutInflater inflater2 = getLayoutInflater();
        View layout2 = inflater2.inflate(R.layout.custom_toast_update, (ViewGroup)findViewById(R.id.dataUpdateToast));

        //        ImageView imageToast = layout.findViewById(R.id.ivToast);
        //        imageToast.setImageResource(R.drawable.toast_train3);
        //        TextView textToast = layout.findViewById(R.id.tvToast);
        //        textToast.setText("Text...");

        dataUpdateToast = new Toast(getApplicationContext());
        dataUpdateToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        dataUpdateToast.setDuration(Toast.LENGTH_SHORT);
        dataUpdateToast.setView(layout2);

    }

    public void updateTimetable(View view) {

        String name = routeName.getText().toString().trim();
        String date = trainDate.getText().toString().trim();
        String aTime = arriveTime.getText().toString().trim();
        String dTime = departTime.getText().toString().trim();
        String tName = trainName.getText().toString().trim();

        TimetableModel tm = new TimetableModel(id, name , date, aTime, dTime, tName);

        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(this);

        int result = DBHelper.updateTimetable(tm);

        if(result > 0){
            //Toast.makeText(this, "Timetable Updated", Toast.LENGTH_SHORT).show();
            dataUpdateToast.show();
            finish();
        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    //Arrive Time
    public void popTimePickerUT(View view) {

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

    //Depart Time
    public void popTimePickerDepartUT(View view) {

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
}
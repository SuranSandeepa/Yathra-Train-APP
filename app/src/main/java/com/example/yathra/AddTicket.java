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
import com.example.yathra.Model.TicketModel;

public class AddTicket extends AppCompatActivity {

    EditText TCDate, TCTime, TCClass, TCFrom, TCTo, TCPrice ;
    Toast dataSavedToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);

        TCDate = findViewById(R.id.editTextTicketDate);
        TCTime = findViewById(R.id.editTextTicketTime);
        TCClass = findViewById(R.id.editTextClassType);
        TCFrom = findViewById(R.id.editTextStartFrom);
        TCTo = findViewById(R.id.editTextEndTo);
        TCPrice = findViewById(R.id.editTextPrice);

        //Custom Toast Message For Data Saved
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.dataSavedToast));

        dataSavedToast = new Toast(getApplicationContext());
        dataSavedToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        dataSavedToast.setDuration(Toast.LENGTH_SHORT);
        dataSavedToast.setView(layout);

    }

    public void insertTicket(View view) {

        String date = TCDate.getText().toString().trim();
        String time = TCTime.getText().toString().trim();
        String classType = TCClass.getText().toString().trim();
        String from = TCFrom.getText().toString().trim();
        String to = TCTo.getText().toString().trim();
        String price = TCPrice.getText().toString().trim();

        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(AddTicket.this);

        TicketModel ticketM = new TicketModel(date, time, classType, from, to, price);

        long result = DBHelper.addTicket(ticketM);

        if(result > 0){
            //Toast.makeText(this, "Ticket Saved" + result, Toast.LENGTH_SHORT).show();
            dataSavedToast.show();
        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

//    public void ShowTickets(View view) {
//        startActivity(new Intent(AddTicket.this, DisplayTicket.class));
//    }

    private class Ticket {
    }
}
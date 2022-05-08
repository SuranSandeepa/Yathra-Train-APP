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
import com.example.yathra.Model.TicketModel;

public class UpdateTicket extends AppCompatActivity {

    EditText TCDate, TCTime, TCClass, TCFrom, TCTo, TCPrice ;
    int id;
    Toast dataUpdateToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ticket);

        TicketModel ticketM = (TicketModel) getIntent().getExtras().getSerializable("TicketModel");

        id = ticketM.getId();
        TCDate = findViewById(R.id.editTextTicketDateUT);
        TCTime = findViewById(R.id.editTextTicketTimeUT);
        TCClass = findViewById(R.id.editTextClassTypeUT);
        TCFrom = findViewById(R.id.editTextStartFromUT);
        TCTo = findViewById(R.id.editTextEndToUT);
        TCPrice = findViewById(R.id.editTextPriceUT);

        TCDate.setText(ticketM.getDate());
        TCTime.setText(ticketM.getTime());
        TCClass.setText(ticketM.getClassType());
        TCFrom.setText(ticketM.getFrom());
        TCTo.setText(ticketM.getTo());
        TCPrice.setText(ticketM.getPrice());

        //Custom Toast Message For Update Data
        LayoutInflater inflater2 = getLayoutInflater();
        View layout2 = inflater2.inflate(R.layout.custom_toast_update, (ViewGroup)findViewById(R.id.dataUpdateToast));

        dataUpdateToast = new Toast(getApplicationContext());
        dataUpdateToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        dataUpdateToast.setDuration(Toast.LENGTH_SHORT);
        dataUpdateToast.setView(layout2);

    }

    public void updateTicket(View view) {

        String date = TCDate.getText().toString().trim();
        String time = TCTime.getText().toString().trim();
        String classType = TCClass.getText().toString().trim();
        String from = TCFrom.getText().toString().trim();
        String to = TCTo.getText().toString().trim();
        String price = TCPrice.getText().toString().trim();

        TicketModel ticketM = new TicketModel(id, date, time, classType, from, to, price);

        YathraDatabaseHelper DBHelper = new YathraDatabaseHelper(this);
        int result = DBHelper.updateTicket(ticketM);

        if(result > 0){
            //Toast.makeText(this, "Ticket Updated" + result, Toast.LENGTH_SHORT).show();
            dataUpdateToast.show();
            finish();
        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TicketHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_home);
    }

    public void displayAddTicketPage(View view) {
        startActivity(new Intent(TicketHome.this, AddTicket.class));
    }

    public void displayTicketsPage(View view) {
        startActivity(new Intent(TicketHome.this, DisplayTicket.class));
    }
}
package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.yathra.Adapters.TicketAdapter;
import com.example.yathra.Database.YathraDatabaseHelper;
import com.example.yathra.Model.TicketModel;

import java.util.ArrayList;

public class DisplayTicket extends AppCompatActivity {

    ArrayList<TicketModel> ticketM;
    TextView ticketTableTotalTT;
    RecyclerView recyclerView;
    TicketAdapter ticketAdapter;
    YathraDatabaseHelper DBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ticket);

        recyclerView = findViewById(R.id.recycleViewDTC);
        ticketTableTotalTT = findViewById(R.id.ticketTableTotalTT);

        DBHelper = new YathraDatabaseHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        ticketM = DBHelper.getAllTickets();
        ticketTableTotalTT.setText("Total Saved Tickets : " + ticketM.size() );

        ticketAdapter = new TicketAdapter(ticketM,this);
        recyclerView.setAdapter(ticketAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
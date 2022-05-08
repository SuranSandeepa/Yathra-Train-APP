package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class StopWatchPage extends AppCompatActivity {


    private int second = 0;
    private  boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch_page);

        startTimer();
    }

    public void onstart(View view){

        running = true;
    }

    public void onstop(View view){

        running = false;
    }

    public void onreset(View view){

        running = false;
        second = 0;
    }

    private void startTimer(){
        final TextView timer = findViewById(R.id.tvStopWatch);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hrs = second/3600;
                int mins = (second%3600)/60;
                int sec = second%60;
                String time = String.format("%02d:%02d:%02d",hrs,mins,sec);
                timer.setText(time);

                if(running){
                    second++;
                }
                handler.postDelayed(this,0);
            }
        });
    }
}
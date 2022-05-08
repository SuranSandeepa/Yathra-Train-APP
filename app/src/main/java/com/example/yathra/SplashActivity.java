package com.example.yathra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        icon = findViewById(R.id.animationIconSplash);

        icon.animate().rotation(360f).setDuration(4000);

        Thread tread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(5000);

                            startActivity(new Intent(SplashActivity.this, HomePage.class));

                            finish();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        tread.start();
    }
}
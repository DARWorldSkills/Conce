package com.aprendiz.ragp.consentrese;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Menu.class);
                startActivity(intent);
                finish();
            }
        };

        timer.schedule(timerTask, 2000);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.purge();
        timer.cancel();

    }


}

package com.aprendiz.ragp.consentrese;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    Timer timer = new Timer();

    public static String [] nombres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


            }

            @Override
            public void onFinish() {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.jugadores_inicio,null);

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Splash.this);
                final EditText txtjugador1 = view.findViewById(R.id.txtjugador1I);
                final EditText txtjugador2 = view.findViewById(R.id.txtjugador2I);
                final Button btnguardar = view.findViewById(R.id.btnGuardarJ);

                btnguardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (txtjugador1.getText().toString().length()>0 && txtjugador2.getText().toString().length()>0) {
                            nombres = new String[]{txtjugador1.getText().toString(), txtjugador2.getText().toString()};
                            Intent intent = new Intent(Splash.this, Menu.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(Splash.this, "Por favor ingrese los jugadores", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertDialog.setView(view);
                alertDialog.setCancelable(false);
                alertDialog.show();
            }
        }.start();



    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void ingresarJugadores(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.purge();
        timer.cancel();

    }



}

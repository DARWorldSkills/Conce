package com.aprendiz.ragp.consentrese;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Puntuacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
    }


    public void enviar(View view) {
        finish();
    }
}

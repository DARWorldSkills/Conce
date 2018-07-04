package com.aprendiz.ragp.consentrese;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

public class Configuracion extends AppCompatActivity {
    RadioButton rbtnFacil,rbtnMedio,rbtnDificil;

    public static int nivel=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        inizialite();
    }

    private void inizialite() {
        rbtnFacil = findViewById(R.id.rbtnFacil);
        rbtnMedio = findViewById(R.id.rbtnMedio);
        rbtnDificil = findViewById(R.id.rbtnDificil);
    }

    public int input_mode_game(){
        int tmp=0;
        if (rbtnFacil.isChecked()){
            tmp=4;
        }
        if (rbtnMedio.isChecked()){
            tmp=6;
        }
        if (rbtnDificil.isChecked()){
            tmp=8;
        }
        return tmp;
    }
}

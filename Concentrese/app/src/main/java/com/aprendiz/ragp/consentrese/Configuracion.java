package com.aprendiz.ragp.consentrese;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Configuracion extends AppCompatActivity {
    RadioButton rbtnFacil,rbtnMedio,rbtnDificil, rbtnTiempo, rbtnPuntuacion;

    public static int nivel=4;
    SharedPreferences jugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        inizialite();
        jugar= getSharedPreferences("juegoC",MODE_PRIVATE);
        inputData();

    }

    private void inizialite() {
        rbtnFacil = findViewById(R.id.rbtnFacil);
        rbtnMedio = findViewById(R.id.rbtnMedio);
        rbtnDificil = findViewById(R.id.rbtnDificil);
    }

    public void inputData(){
        int tmp1 = jugar.getInt("nivel",4);
        if (tmp1 == 4){
            rbtnFacil.setChecked(true);
        }
        if (tmp1 == 6){
            rbtnMedio.setChecked(true);
        }
        if (tmp1 == 8){
            rbtnDificil.setChecked(true);
        }

        int tmp2 = jugar.getInt("modo", 1);

        if (tmp2 == 1){
            rbtnTiempo.setChecked(true);
        }
        if (tmp2 == 2){
            rbtnPuntuacion.setChecked(true);
        }

    }

    public int input_dificulty(){
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

    public int input_mode_game(){
        int tmp=0;
        if (rbtnTiempo.isChecked()){
            tmp=1;
        }else {
            if (rbtnPuntuacion.isChecked()){
                tmp=2;
            }else {
                tmp=1;
            }
        }

        return tmp;


    }

    public void salir(View view) {
        SharedPreferences jugar= getSharedPreferences("juegoC",MODE_PRIVATE);
        SharedPreferences.Editor editor = jugar.edit();
        editor.putInt("nivel",input_dificulty());
        editor.putInt("modo",input_mode_game());
        editor.commit();
        nivel=input_dificulty();

        finish();
    }
}

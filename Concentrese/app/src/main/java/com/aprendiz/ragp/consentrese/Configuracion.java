package com.aprendiz.ragp.consentrese;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Configuracion extends AppCompatActivity {
    RadioButton rbtnTiempo, rbtnPuntuacion;
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
        rbtnTiempo = findViewById(R.id.rbtnTiempo);
        rbtnPuntuacion = findViewById(R.id.rbtnPuntuacion);
    }

    public void inputData(){
        int tmp2 = jugar.getInt("modo", 1);

        if (tmp2 == 1){
            rbtnTiempo.setChecked(true);
        }
        if (tmp2 == 2){
            rbtnPuntuacion.setChecked(true);
        }

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
        editor.putInt("modo",input_mode_game());
        editor.commit();

        finish();
    }
}

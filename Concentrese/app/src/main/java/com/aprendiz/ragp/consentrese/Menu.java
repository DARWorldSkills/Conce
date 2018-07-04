package com.aprendiz.ragp.consentrese;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Menu extends AppCompatActivity {

    public static int dificultad =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }


    public void Enviar(View view) {
        Intent intent = new Intent(Menu.this, Puntuacion.class);
        startActivity(intent);


    }

    public void ir(View view) {
        Intent intent = new Intent(Menu.this, Configuracion.class);
        startActivity(intent);
    }

    public void go(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View vista = layoutInflater.inflate(R.layout.dificultad_layout,null);
        final RadioButton rbtnfacil = vista.findViewById(R.id.rbtnFacilD);
        final RadioButton rbtnmedio = vista.findViewById(R.id.rbtnMedioD);
        final RadioButton rbtndificil = vista.findViewById(R.id.rbtnDificilD);
        rbtnfacil.setChecked(true);

        builder.setTitle("Elija la difucultad");
        builder.setView(vista);
        builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                    if (rbtnfacil.isChecked()) {
                        dificultad = 4;
                    }
                    if (rbtnmedio.isChecked()) {
                        dificultad = 6;
                    }
                    if (rbtndificil.isChecked()) {
                        dificultad = 8;
                    }

                    Intent intent = new Intent(Menu.this, JuegoF.class);
                    startActivity(intent);



            }
        });

        builder.show();

    }
}

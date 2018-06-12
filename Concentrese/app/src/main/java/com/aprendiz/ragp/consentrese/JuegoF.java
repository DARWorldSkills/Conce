package com.aprendiz.ragp.consentrese;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class JuegoF extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    List<Integer> lista1 = new ArrayList<>();

    int modo_juego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_f);
        inizialite();
    }

    private void inizialite() {
        btn1 =findViewById(R.id.btnFacil1);
        btn2 =findViewById(R.id.btnFacil2);
        btn3 =findViewById(R.id.btnFacil3);
        btn4 =findViewById(R.id.btnFacil4);
        btn5 =findViewById(R.id.btnFacil5);
        btn6 =findViewById(R.id.btnFacil6);
        btn7 =findViewById(R.id.btnFacil7);
        btn8 =findViewById(R.id.btnFacil8);
    }

    public void randomizarImagenes(){
        List<Integer> tmplista = new ArrayList<>();
        List<Integer> resultado = new ArrayList<>();
        int tmpR=0;
        tmplista.add(1);
        tmplista.add(2);
        tmplista.add(3);
        tmplista.add(4);
        tmplista.add(5);
        tmplista.add(6);
        tmplista.add(7);
        tmplista.add(8);



        while (tmplista.size()<8){
            tmpR = (int) (Math.round(Math.random()*8)+1);
            resultado.add(tmpR);
            boolean bandera = false;

        }


    }



}

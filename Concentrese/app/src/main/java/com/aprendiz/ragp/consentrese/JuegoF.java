package com.aprendiz.ragp.consentrese;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class JuegoF extends AppCompatActivity {

    private int fondoJuego = R.drawable.boton;

    private int[] imgFondo, imgAleatorias;
    private List<Integer> imgSelec;
    private GridView gridJuego;
    private int[] imagenesJuego = {
            R.drawable.alemania,
            R.drawable.argentina,
            R.drawable.brasil,
            R.drawable.colombia,
            R.drawable.eeuu,
            R.drawable.japon,
            R.drawable.mexico,
            R.drawable.panama,
            R.drawable.peru,
            R.drawable.uruguay,

    };
    int nivel = 4;
    int desde = 0;
    String tipoJuego = "m";
    TextView tvjuego;
    int numeroMov = 0, pos1 = -1, pos2 = -1, canselec = 0, salir = 0;
    ImageView img1, img2;

    int modo_juego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_f);
        gridJuego= (GridView) findViewById(R.id.contenedorJuego);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density  = (int) getResources().getDisplayMetrics().density;
        int dpHeight = (int)metrics.heightPixels / density;
        int dpWidth  = (int)metrics.widthPixels / density;

        int ancho=100;
        int alto=100;

        if (nivel == 4) {
            ancho=dpWidth / 3;
            alto=dpHeight / 5;

            ancho=ancho+30;
            alto=alto+30;
            gridJuego.setPadding(10, 10, 0, 0);
            gridJuego.setNumColumns(2);
        }

        if (nivel == 6) {
            ancho=dpWidth / 4;
            alto=dpHeight / 5;

            ancho=ancho+30;
            alto=alto+30;

            gridJuego.setPadding(10, 10, 0, 0);
            gridJuego.setNumColumns(3);
        }
        if (nivel == 8) {
            ancho=dpWidth / 5;
            alto=dpHeight / 5;

            ancho=ancho+30;
            alto=alto+30;


            gridJuego.setPadding(10, 10, 0, 0);
            gridJuego.setNumColumns(4);
        }



        Jugar();
        final AdapterJ adaptadorJuego = new AdapterJ(imgFondo, ancho,alto, JuegoF.this);
        gridJuego.setAdapter(adaptadorJuego);
        gridJuego.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView item = (ImageView) view;
                canselec++;
                if (pos1 == position || pos2 == position) {
                    canselec--;
                    return;
                }
                if (canselec == 1) {
                    img1 = item;
                    pos1 = position;
                }
                if (canselec == 2) {
                    img2 = item;
                    pos2 = position;
                }

                mostarImagen(item, position);
            }
        });

    }

    public void mostarImagen(ImageView item, int pos) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inSampleSize = 5;
        Bitmap imagen = BitmapFactory.decodeResource(getResources(), imgAleatorias[pos], opt);
        item.setImageBitmap(imagen);
        if (canselec == 2) {
            numeroMov++;
            new validarJuego().execute();
        }
    }

    public class validarJuego extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            gridJuego.setEnabled(false);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (imgAleatorias[pos1] == imgAleatorias[pos2]) {
                img1.setVisibility(View.INVISIBLE);
                img2.setVisibility(View.INVISIBLE);
                img1 = null;
                img2 = null;
                salir--;
            }

            if (img1 != null && img2 != null) {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inSampleSize = 2;
                Bitmap imagen = BitmapFactory.decodeResource(getResources(), fondoJuego, opt);
                img1.setImageBitmap(imagen);
                Bitmap imagen2 = BitmapFactory.decodeResource(getResources(), fondoJuego, opt);
                img2.setImageBitmap(imagen2);
            }
            canselec = 0;
            pos1 = -1;
            pos2 = -1;
            gridJuego.setEnabled(true);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void generarFondo() {
        imgFondo = new int[nivel * 2];
        for (int i = 0; i < imgFondo.length; i++) {
            imgFondo[i] = fondoJuego;
        }
    }

    public void generarSeleccionadas() {
        imgSelec = new ArrayList<>();
        for (int i = 0; i < nivel; i++) {
            int aux = (int) (Math.random() * nivel);
            if (!imgSelec.contains(imagenesJuego[aux])) {
                imgSelec.add(imagenesJuego[aux]);
            } else {
                i--;
            }
        }

    }

    public void generaAleatorias() {
        imgAleatorias = new int[nivel * 2];
        for (int i = 0; i < nivel; i++) {
            int aux = 0;
            do {
                int val = (int) (Math.random() * nivel * 2);
                if (imgAleatorias[val] == 0) {
                    imgAleatorias[val] = imgSelec.get(i);
                    aux++;
                }
            } while (aux < 2);
        }
    }

    public void Jugar() {
        generarFondo();
        generarSeleccionadas();
        generaAleatorias();

    }



}

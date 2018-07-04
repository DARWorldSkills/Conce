package com.aprendiz.ragp.consentrese;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView tvjuego, txtnombreJugador1, txtnombreJugador2, txtpuntuacionJugador1, txtpuntuacionJugador2, txtTiempo;
    int numeroMov = 0, pos1 = -1, pos2 = -1, canselec = 0, salir = 0;
    ImageView img1, img2;
    boolean bandera = true;
    int modo_juego, inicioJugador, puntuacionJ1, puntuacionJ2;
    SharedPreferences jugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_f);
        puntuacionJ1=0; puntuacionJ2=0;
        gridJuego= (GridView) findViewById(R.id.contenedorJuego);
        inizialite();
        inputData();
        turnos();
        bandera=true;
        jugar= getSharedPreferences("juegoC",MODE_PRIVATE);
        nivel = Menu.dificultad;
        modo_juego = jugar.getInt("modo",1);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density  = (int) getResources().getDisplayMetrics().density;
        int dpHeight = (int)metrics.heightPixels / density;
        int dpWidth  = (int)metrics.widthPixels / density;

        int ancho=100;
        int alto=100;
        salir = nivel;

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
        if (inicioJugador==1){
            txtnombreJugador1.setTextColor(getColor(R.color.colorVerde));
            Toast.makeText(JuegoF.this, "Empieza jugador 1", Toast.LENGTH_SHORT).show();
            txtpuntuacionJugador1.setText("Puntuación: "+puntuacionJ1);
        }

        if (inicioJugador==2){
            txtnombreJugador2.setTextColor(getColor(R.color.colorVerde));
            Toast.makeText(JuegoF.this, "Empieza jugador 2", Toast.LENGTH_SHORT).show();
            txtpuntuacionJugador2.setText("Puntuación: "+puntuacionJ2);
        }

    }

    public void turnos(){
        inicioJugador= ((int) (Math.random() *2 ))+1;

    }

    public void inizialite(){
        txtnombreJugador1 = findViewById(R.id.txtnombreJ1);
        txtnombreJugador2 = findViewById(R.id.txtnombreJ2);
        txtpuntuacionJugador1 = findViewById(R.id.txtPuntuacionJ1);
        txtpuntuacionJugador2 = findViewById(R.id.txtPuntuacionJ2);
        txtTiempo =findViewById(R.id.txtTiempoJuego);
    }

    public void inputData(){

        txtnombreJugador1.setText(Splash.nombres[0]);
        txtnombreJugador2.setText(Splash.nombres[1]);

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

            if (inicioJugador==1){
                txtnombreJugador1.setTextColor(getColor(R.color.colorGris));
                txtnombreJugador2.setTextColor(getColor(R.color.colorVerde));
                Toast.makeText(JuegoF.this, "Continua jugador 2", Toast.LENGTH_SHORT).show();
                inicioJugador=2;
                txtpuntuacionJugador1.setText("Puntuación: "+puntuacionJ1);
            }else {

                if (inicioJugador == 2) {
                    txtnombreJugador2.setTextColor(getColor(R.color.colorGris));
                    txtnombreJugador1.setTextColor(getColor(R.color.colorVerde));
                    Toast.makeText(JuegoF.this, "Continua jugador 1", Toast.LENGTH_SHORT).show();
                    inicioJugador = 1;
                }
            }
            if (imgAleatorias[pos1] == imgAleatorias[pos2]) {
                img1.setVisibility(View.INVISIBLE);
                img2.setVisibility(View.INVISIBLE);
                img1 = null;
                img2 = null;
                salir--;

                if (inicioJugador==2){
                    puntuacionJ1+=100;
                    txtpuntuacionJugador1.setText("Puntuación: "+puntuacionJ1);

                }

                if (inicioJugador==1){
                    puntuacionJ2+=100;
                    txtpuntuacionJugador2.setText("Puntuación: "+puntuacionJ2);
                }

                if (salir==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JuegoF.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.juego_terminado_layout, null);
                    TextView txtJugador1 = view.findViewById(R.id.txtNombreJ1L);
                    TextView txtJugador2 = view.findViewById(R.id.txtNombreJ2L);
                    TextView txtpuntuacion1 = view.findViewById(R.id.txtPuntuacionJ1L);
                    TextView txtpuntuacion2 = view.findViewById(R.id.txtPuntuacionJ2L);
                    TextView txttiempo = view.findViewById(R.id.txtTiempoP);

                    txtJugador1.setText(txtnombreJugador1.getText().toString());
                    txtJugador2.setText(txtnombreJugador2.getText().toString());
                    txtpuntuacion1.setText(txtpuntuacionJugador1.getText().toString());
                    txtpuntuacion2.setText(txtpuntuacionJugador2.getText().toString());
                    txttiempo.setText(txtTiempo.getText().toString());


                    builder.setView(view);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setTitle("Juego terminado");
                    builder.show();

                }
            }

            if (img1 != null && img2 != null) {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inSampleSize = 2;
                Bitmap imagen = BitmapFactory.decodeResource(getResources(), fondoJuego, opt);
                img1.setImageBitmap(imagen);
                Bitmap imagen2 = BitmapFactory.decodeResource(getResources(), fondoJuego, opt);
                img2.setImageBitmap(imagen2);

                if (inicioJugador==2){
                    puntuacionJ1-=1;
                    txtpuntuacionJugador1.setText("Puntuación: "+puntuacionJ1);

                }

                if (inicioJugador==1){
                    puntuacionJ2-=1;
                    txtpuntuacionJugador2.setText("Puntuación: "+puntuacionJ2);
                }
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
        tiempo();

    }

    private void tiempo() {
        final int[] segundos = {0};
        txtTiempo.setText(Integer.toString(segundos[0]));
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            segundos[0] +=1;

                            txtTiempo.setText(Integer.toString(segundos[0]));

                        }
                    });

                }

            }
        });
        thread.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        bandera= false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        bandera= false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}

package com.aprendiz.ragp.consentrese;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class AdapterJ extends BaseAdapter {
    private int[] imagenesJuego;
    private int ancho;
    private int alto;
    private Context context;

    public AdapterJ(int[] imagenesJuego, int ancho, int alto, Context context) {
        this.imagenesJuego = imagenesJuego;
        this.ancho = ancho;
        this.alto = alto;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagenesJuego.length;
    }

    @Override
    public Object getItem(int position) {
        return imagenesJuego[position];
    }

    @Override
    public long getItemId(int position) {
        return imagenesJuego[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView item = new ImageView(context);
        item.setScaleType(ImageView.ScaleType.FIT_XY);

        item.setLayoutParams(new GridView.LayoutParams(ancho, alto));
        item.setPadding(8, 8, 8, 8);
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inSampleSize = 2;
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), imagenesJuego[position], opt);
        item.setImageBitmap(bm);
        return item;
    }
}

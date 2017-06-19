package com.memory.adrhm.memory;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * @author : hansjulien on 29/05/2017.
 * Classe qui gère l'affichage des images pour le jeu
 * remplit la grille avec les images
 */
public class CardGameAdapter extends BaseAdapter {

    private Context context;
    private Game game;

    public CardGameAdapter(Context c, Game Game) {
        context = c;
        this.game = Game;
    }

    public int getCount() {
        return game.getNbImages()*2;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    /******************** LA MÉTHODE DE GUIGUI RETOUCHÉE ***************************/

   @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CardGame card;

        if(convertView == null){
            card = new CardGame(context);
            Resources r = Resources.getSystem();
            float pixels = 0;

            // On définit la taille des images par rapport au niveau choisi
            switch (SelectGameActivity.getTitleLevel()) {
                case "Facile" :
                    pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 175, r.getDisplayMetrics());
                    break;
                case "Moyen" :
                    pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, r.getDisplayMetrics());
                    break;
                case "Difficile" :
                    pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 140, r.getDisplayMetrics());
                    break;
            }
            card.setLayoutParams(new GridView.LayoutParams((int) pixels, (int) pixels));

        } else {
            card = (CardGame) convertView;
        }

        card.setImageResource(game.getImageAt(position));
        return card;
    }


    /******************** LA MÉTHODE DE GUIGUI ORIGINALE ***************************/

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent){
        CardGame card;

        if(convertView == null){
            card = new CardGame(context);
            float pixels = 0;
            if(getWidthDeviceDp(context) >= 360){
                
                switch (SelectGameActivity.getTitleLevel()) {
                    case "Facile" :
                        pixels = convertDpToPixel(175, context);
                        break;
                    case "Moyen" :
                        pixels = convertDpToPixel(170, context);
                        break;
                    case "Difficile" :
                        pixels = convertDpToPixel(140, context);
                        break;
                }
            }else{
                pixels = convertDpToPixel(62,context);
            }
            card.setLayoutParams(new GridView.LayoutParams((int) pixels, (int) pixels));
            card.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else{
            card = (CardGame) convertView;
        }
        card.setImageResource(game.getImageAt(position));

        return card;
    }*/

    /******************** FONCTIONNEL MAIS PAS OPTI ***************************/

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.image_and_back, null);
        //convertView.setMinimumHeight(getHeight(context));
        holder = new ViewHolder();
        holder.image = (ImageView) convertView
                .findViewById(R.id.card_image);

        holder.imageBack = (ImageView) convertView
                .findViewById(R.id.card_back_image);
        holder.image.setImageResource(game.getImageAt(position));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }*/

    /******************** LA MEILLEURE A CE JOUR (MIXÉE AVEC GUIGUI AU  DESSUS) ***************************/

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {

            //Calculation of ImageView Size - density independent.
            //maybe you should do this calculation not exactly in this method but put is somewhere else.
            Resources r = Resources.getSystem();
            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());


            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams((int)px, (int)px));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(game.getImageAt(position));
        return imageView;
    }*/

    //Convertit les dp en pixel
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    //Convertit les pixels en dp
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    //Renvoie la taille de la largeur(la plus petite mesure) de l'appareil
    public static int getWidthDeviceDp(Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return Math.min((int) convertPixelsToDp(dm.widthPixels, context), (int)convertPixelsToDp(dm.heightPixels,context));
    }


}

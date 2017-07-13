package com.memory.adrhm.memory;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * @author : hansjulien on 29/05/2017.
 * Classe qui gère l'affichage des images pour le jeu
 * remplit la grille avec les images
 */
class CardGameAdapter extends BaseAdapter {

    private Context context;
    private Game game;
    private int pixels;

    CardGameAdapter(Context c, Game Game) {
        context = c;
        this.game = Game;
    }

    public int getCount() {
        return game.getNbImages() * 2;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardGame card;

        if (convertView == null) {
            card = new CardGame(context);
            Resources r = Resources.getSystem();
            pixels = 0;

            // On définit la taille des images par rapport au niveau choisi
            switch (SelectGameActivity.getTitleLevel()) {
                case "Facile":
                    pixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 175, r.getDisplayMetrics());
                    break;
                case "Moyen":
                    pixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, r.getDisplayMetrics());
                    break;
                case "Difficile":
                    pixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 140, r.getDisplayMetrics());
                    break;
            }
            card.setLayoutParams(new GridView.LayoutParams(pixels, pixels));

        } else {
            card = (CardGame) convertView;
        }

        card.setImageResource(game.getImageAt(position), pixels);
        return card;
    }
}
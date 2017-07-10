package com.memory.adrhm.memory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author : hansjulien on 29/05/2017.
 * Classe qui définit une image dans la grille de jeu
 * contient deux images, une pour le jeu et un dos quand elle est face cachée
 */
public class CardGame extends RelativeLayout {

    private LayoutInflater inflater;
    // Image du jeu
    private ImageView image;
    // Dos de l'image
    private ImageView back;

    //Les trois constructeurs nécessaires selon les différentes versions des utilisateurs
    public CardGame(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
        init();

    }

    public CardGame(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        init();
    }

    public CardGame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);
        init();
    }

    // Inflate l'inflater et va chercher les deux images pour pouvoir les utiliser
    private void init() {
        inflater.inflate(R.layout.image_and_back, this, true);
        image = (ImageView) findViewById(R.id.card_image);
        back = (ImageView) findViewById(R.id.card_back_image);
    }

    // Change l'image pour celle souhaitée
    public void setImageResource(Integer resource) {
        if (image != null) {
            image.setImageResource(resource);
        }
    }

    // Fonction qui retourne l'image face image
    public void returnCard() {
        back.setVisibility(GONE);
    }

    // Fonction qui retourne l'image face cachée
    public void hideCard() {
        back.setVisibility(VISIBLE);
    }

    // Fonction qui cache une carte (utilisé quand 2 cartes identiques sont trouvées)
    public void removeCard() {
        image.setVisibility(GONE);
    }
}
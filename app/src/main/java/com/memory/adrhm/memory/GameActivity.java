package com.memory.adrhm.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @author : hansjulien on 29/05/2017.
 */

/**
 * Classe (Activité) qui gère l'affichage de la grille de jeu
 * en fonction du niveau sélectionné et du choix des images
 */

public class GameActivity extends AppCompatActivity {

    private GridView gridview;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent i = getIntent();
        String cat = getIntent().getStringExtra("categorie");
        gridview = (GridView)findViewById(R.id.gridView);
        //Log.e("MEMORY","categorie: " + CardListViewHolder.getValue() + "  " + "level: " + SelectGameActivity.getTitleLevel());
        //Log.e("LENGTH-ARRAY", String.valueOf(Game.availableImages.length));
        game = new Game(Game.availableImages.length);
        gridview.setNumColumns(4);
        //gridview.setMinimumHeight(CardGameAdapter.getHeight(this)/4);
        gridview.setVerticalSpacing(20);
        gridview.setAdapter(new CardGameAdapter(this, game));

        /**
         * @// TODO: 01/06/2017
         * ON RECUPERE LA CATEGORIE ET LE NIVEAU : ON A TOUT POUR CREER LES DIFFERENTES GRILLES SELON LES NIVEAUX
         * METTRE LE NOMBRE DE COLONNES EN AUTO_FIT ET ON SET APRES
         */

        if (SelectGameActivity.getTitleLevel().equals("Facile") && cat.equals("Personnages")) {

        }
    }
}

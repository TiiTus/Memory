package com.memory.adrhm.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent i = getIntent();
        String cat = getIntent().getStringExtra("categorie");
        gridview = (GridView)findViewById(R.id.gridView);
        //Log.e("MEMORY","categorie: " + CardListViewHolder.getValue() + "  " + "level: " + SelectGameActivity.getTitleLevel());
        //Log.e("LENGTH-ARRAY", String.valueOf(Game.hardImages.length));

        //gridview.setMinimumHeight(CardGameAdapter.getHeight(this)/4);


        /**
         * @// TODO: 01/06/2017
         * ON RECUPERE LA CATEGORIE ET LE NIVEAU : ON A TOUT POUR CREER LES DIFFERENTES GRILLES SELON LES NIVEAUX
         * METTRE LE NOMBRE DE COLONNES EN AUTO_FIT ET ON SET APRES
         */

        if (SelectGameActivity.getTitleLevel().equals("Facile") /*&& cat.equals("Personnages")*/) {
            // 4x4 - 8 images
            gridview.setNumColumns(4);
            game = new Game(6);
        }

        if (SelectGameActivity.getTitleLevel().equals("Moyen")) {
            // 5x4 - 10 images
            gridview.setNumColumns(5);
            game = new Game(10);
        }

        if (SelectGameActivity.getTitleLevel().equals("Difficile") /*&& cat.equals("Personnages")*/) {
            // 6x6 - 18 images
            gridview.setNumColumns(6);
            game = new Game(18);
        }

        gridview.setVerticalSpacing(20);
        gridview.setAdapter(new CardGameAdapter(this, game));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final CardGame clickedCard = (CardGame) view;

                clickedCard.reveal();
            }
        });
    }
}

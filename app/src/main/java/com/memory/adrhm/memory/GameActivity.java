package com.memory.adrhm.memory;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

/**
 * @author : hansjulien on 29/05/2017.
 * Classe (Activité) qui gère l'affichage de la grille de jeu
 * en fonction du niveau sélectionné et du choix des images
 */

public class GameActivity extends AppCompatActivity {

    private GridView gridview;
    private Game game;
    private int strokes;
    private boolean isLocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridview = (GridView)findViewById(R.id.gridView);

        // Configuration de la grille selon le niveau choisit
        if (SelectGameActivity.getTitleLevel().equals("Facile")) {
            // 4x4 - 8 images
            gridview.setNumColumns(4);
            game = new Game(8);
        }

        if (SelectGameActivity.getTitleLevel().equals("Moyen")) {
            // 5x4 - 10 images
            gridview.setNumColumns(5);
            game = new Game(10);
        }

        if (SelectGameActivity.getTitleLevel().equals("Difficile")) {
            // 6x5 - 15 images
            gridview.setNumColumns(6);
            game = new Game(15);
        }

        gridview.setVerticalSpacing(20);
        gridview.setAdapter(new CardGameAdapter(this, game));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final CardGame clickedCard = (CardGame) view;
                clickedCard.returnCard();
                /* GAME */
            }
        });
    }
}

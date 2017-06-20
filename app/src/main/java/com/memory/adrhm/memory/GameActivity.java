package com.memory.adrhm.memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * @author : hansjulien on 29/05/2017.
 * Classe (Activité) qui gère l'affichage de la grille de jeu
 * en fonction du niveau sélectionné et du choix des images
 */

public class GameActivity extends AppCompatActivity {

    private Game game;
    // Nombres de coups joués pour affichage à la fin
    private int strokes;
    // Variable qui indique si on doit attendre avant de retourner les 2 cartes
    private boolean isLocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final GridView gridview = (GridView) findViewById(R.id.gridView);

        // Configuration de la grille selon le niveau choisi
        switch (SelectGameActivity.getTitleLevel()) {
            case "Facile":
                // 4x4 - 8 images (facile)
                gridview.setNumColumns(4);
                game = new Game(8);
                break;

            case "Moyen":
                // 5x4 - 10 images (moyen)
                gridview.setNumColumns(5);
                game = new Game(10);
                break;

            case "Difficile":
                // 6x5 - 15 images (difficile)
                gridview.setNumColumns(6);
                game = new Game(15);
                break;
        }

        gridview.setVerticalSpacing(20);
        gridview.setAdapter(new CardGameAdapter(this, game));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final CardGame clickedCard = (CardGame) view;
                strokes++;
                clickedCard.returnCard();
            }
        });
    }
}

package com.memory.adrhm.memory;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    // Nombres de coups joués
    private int strokes;
    // Variable qui indique si on doit attendre avant de retourner les 2 cartes
    private boolean isLocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridview = (GridView)findViewById(R.id.gridView);

        // Configuration de la grille selon le niveau choisit
        if (SelectGameActivity.getTitleLevel().equals("Facile")) {
            // 4x4 - 8 images (facile)
            gridview.setNumColumns(4);
            game = new Game(8);
        }

        if (SelectGameActivity.getTitleLevel().equals("Moyen")) {
            // 5x4 - 10 images (moyen)
            gridview.setNumColumns(5);
            game = new Game(10);
        }

        if (SelectGameActivity.getTitleLevel().equals("Difficile")) {
            // 6x5 - 15 images (difficile)
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

    /**
     * Si fin du jeu on affiche une popup
     * l'utilisateur peut rejouer une partie ou quitter et revenir à la page précédente
      */
    private void endGame(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.replay_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
                startActivity(getIntent());
            }
        });
        builder.setNegativeButton(R.string.quit_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        int nbStrokes = strokes/2;
        String strStrokes = getResources().getString(R.string.title_dialog, nbStrokes);
        builder.setTitle(strStrokes)
                .setMessage(R.string.mess_dialog);

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().getAttributes();

        // Récupération du message dans un Textview pour changer la taille
        TextView message = (TextView) dialog.findViewById(android.R.id.message);
        if (message != null) {
            message.setTextSize(20);
        }

        // Récupération des 2 boutons pour changer la taille
        Button replay = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        replay.setTextSize(18);
        Button quit = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        quit.setTextSize(18);
    }
}

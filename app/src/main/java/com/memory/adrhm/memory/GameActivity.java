package com.memory.adrhm.memory;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


/**
 * @author : hansjulien on 29/05/2017.
 * Classe (Activité) qui gère l'affichage de la grille de jeu
 * en fonction du niveau sélectionné et du choix des images
 */

public class GameActivity extends AppCompatActivity {

    private Game game;
    private GridView gridview;

    // Nombres de coups joués pour affichage à la fin
    private int strokes;
    // Variable qui indique si on doit attendre avant de retourner les 2 cartes
    private boolean isLocked = false;
    // Instance de la classe SoundPool qui gère et joue les ressources audio
    private SoundPool sp;
    // Le son joué
    private int soundId;
    // Variable qui indique si le son est chargé
    private boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        gridview = (GridView) findViewById(R.id.gridView);
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rlGameActivity);

        SharedPreferences settings = getSharedPreferences("myPref", 0);
        final String gamePrefRead = settings.getString("supp_or_not_card_param", "Désactivé");
        Log.e("SHAREDPREFERENCES", gamePrefRead);

        // Construction des outils pour lire les sons
        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        sp = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(attrs)
                .build();
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        soundId = sp.load(getApplicationContext(), R.raw.applause_2, 1);

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

        // On regarde si la catégorie est "Drapeaux" et on fonce la couleur de fond
        // à cause de la couleur blanche sur certains drapeaux
        if (CardListViewHolder.getValue().equals("Drapeaux")) {
            relativeLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.grey_background_flags));
        }
        gridview.setVerticalSpacing(20);
        gridview.setAdapter(new CardGameAdapter(this, game));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Carte choisie
                final CardGame firstClickedCard = (CardGame) view;
                strokes++;
                // si le joueur doit attendre car 2 cartes différentes sont déjà retournées
                if (!isLocked) {
                    if (game.isAlreadyReturned(position)) {
                        // On ne fait rien ou un Toast
                    } else {
                        final int firstCard = game.getFirstCard();
                        if (firstCard == -1) {
                            // Pas de comparaison
                            game.setFirstCard(position);
                            firstClickedCard.returnCard();
                        } else {
                            // Comparaison
                            firstClickedCard.returnCard();
                            boolean match = game.check(position);

                            // Si identique
                            if (match) {
                                // Si la suppression des cartes est activée
                                if (gamePrefRead.equals("Activé")) {
                                // On laisse les 2 cartes identiques 1s avant de les effacer
                                isLocked = true;
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        firstClickedCard.removeCard();
                                        CardGame secondClickedCard = (CardGame) (gridview.getChildAt(firstCard));
                                        secondClickedCard.removeCard();
                                        isLocked = false;
                                    }
                                },1000);
                                }
                                // Si identique on regarde si fin du jeu
                                if (game.finishedGame()) {
                                    if (loaded)
                                        sp.play(soundId, 1, 1, 1, 0, 1f);
                                    endGame();
                                }
                            } else {
                                // On laisse les cartes retournées quelques secondes
                                // et on recache les cartes
                                isLocked = true;
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        firstClickedCard.hideCard();
                                        CardGame secondClickedCard = (CardGame) (gridview.getChildAt(firstCard));
                                        secondClickedCard.hideCard();
                                        isLocked = false;
                                    }
                                },2000);
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Si fin du jeu on affiche une popup qui indique le nombre de coups joués pour gagner
     * l'utilisateur à le choix de :
     * - rejouer une partie
     * - ou de quitter et revenir à la page précédente
     */
    private void endGame(){

        // Affichage des confettis quand la partie est finie
        // By Dion Segijn (https://github.com/DanielMartinus/Konfetti)
        KonfettiView viewKonfetti = (KonfettiView) findViewById(R.id.viewKonfetti);
        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(5000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(20, 6f), new Size(16, 6f))
                .setPosition(-50f, viewKonfetti.getWidth() +50f, -50f, -50f)
                .stream(500, 5000L);

        // Construction et affichage de la popup
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        // Bouton rejouer
        builder.setPositiveButton(R.string.replay_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
                startActivity(getIntent());
            }
        });
        // Bouton quitter
        builder.setNegativeButton(R.string.quit_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        // On divise le nombre de coups par 2 car 1 coup = 1 clique sur une carte
        int nbStrokes = strokes/2;
        String strStrokes = getResources().getString(R.string.title_dialog, nbStrokes);
        builder.setTitle(strStrokes)
                .setMessage(R.string.mess_dialog);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Récupération du message dans un Textview pour changer la taille du texte
        TextView message = (TextView) dialog.findViewById(android.R.id.message);
        if (message != null) {
            message.setTextSize(20);
        }

        // Récupération des 2 boutons pour changer la taille du texte
        Button replay = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        replay.setTextSize(18);
        Button quit = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        quit.setTextSize(18);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sp.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sp.release();
    }

    // Méthode qui demande si l'utilisateur veut vraiment quitter la partie
    // quand celui-ci appuie sur la touche "précédent"
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_dialog_back_pressed)
                .setMessage(R.string.mess_dialog_back_pressed)
                .setNegativeButton(R.string.no_button, null)
                .setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        GameActivity.super.onBackPressed();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

        // Récupération du message dans un Textview pour changer la taille du texte
        TextView message = (TextView) dialog.findViewById(android.R.id.message);
        if (message != null) {
            message.setTextSize(20);
        }

        // Récupération des 2 boutons pour changer la taille du texte
        Button yes = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        yes.setTextSize(18);
        Button no = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        no.setTextSize(18);
    }
}

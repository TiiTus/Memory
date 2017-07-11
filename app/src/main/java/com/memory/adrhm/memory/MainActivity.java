package com.memory.adrhm.memory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;


/**
 * @author : hansjulien on 29/05/2017.
 * Classe (Activité) qui représente le première page de l'application
 * utilisée pour le choix du niveau de difficulté
 */
public class MainActivity extends AppCompatActivity {

    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    // ToggleButton pour la popup paramètres
    private ToggleButton toggleButton;
    // Contient la préférence de l'utilisateur ((des)activé) pour cacher les cartes identiques trouvées
    private String gamePrefRead;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easyButton = (Button)findViewById(R.id.easyButton);
        mediumButton = (Button)findViewById(R.id.mediumButton);
        hardButton = (Button)findViewById(R.id.hardButton);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = (String) easyButton.getText();
                Intent myIntent = new Intent(MainActivity.this, SelectGameActivity.class);
                myIntent.putExtra("level", value);
                startActivity(myIntent);
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = (String) mediumButton.getText();
                Intent myIntent = new Intent(MainActivity.this, SelectGameActivity.class);
                myIntent.putExtra("level", value);
                startActivity(myIntent);
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = (String) hardButton.getText();
                Intent myIntent = new Intent(MainActivity.this, SelectGameActivity.class);
                myIntent.putExtra("level", value);
                startActivity(myIntent);
            }
        });

        // Lecture des préférences enregistrées précédemment
        sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        gamePrefRead = sharedPref.getString("supp_or_not_card_param", "Désactivé");
        Log.e("ENMEMOIRE", gamePrefRead);
    }

    // Inflate le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Lance l'action de l'item du menu quand celui-ci est cliqué
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_credit:
                Intent i = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(i);
                return true;

            case R.id.action_settings:
                showPopUpSettings();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Méthode qui construit une popup qui va permettre à l'utilisateur de choisir
    // la suppression ou non des cartes quand celles-ci sont trouvées
    private void showPopUpSettings() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_parameter_dialog, null);
        builder.setView(v);
        builder.setTitle(R.string.title_dialog_parameter);

        toggleButton = (ToggleButton) v.findViewById(R.id.togglebutton);

        // Si la préférence est sur la position "Activé" on met le visuel du bouton sur "Activé"
        if (gamePrefRead.equals("Activé")) {
            toggleButton.setChecked(true);
        }

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    Toast.makeText(MainActivity.this, "La suppression des cartes est activée", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "La suppression des cartes est désactivée", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // bouton enregistrer le choix
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // "Activé" ou "Désactivé"
                String gamePrefSaved = String.valueOf(toggleButton.getText());
                // Enregistrement du choix de l'utilisateur de supprimer les cartes ou pas dans les SharedPreferences
                sharedPref = getApplicationContext().getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("supp_or_not_card_param", gamePrefSaved);
                editor.apply();
            }
        });
        //bouton annuler
        builder.setNegativeButton(R.string.button_cancel, null);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Récupération des 2 boutons pour changer la taille du texte
        Button ok = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        ok.setTextSize(18);
        Button cancel = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        cancel.setTextSize(18);
    }
}

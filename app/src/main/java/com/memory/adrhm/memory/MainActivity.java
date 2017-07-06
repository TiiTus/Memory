package com.memory.adrhm.memory;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
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
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easyButton = (Button)findViewById(R.id.easyButton);
        mediumButton = (Button)findViewById(R.id.mediumButton);
        hardButton = (Button)findViewById(R.id.hardButton);
        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.constraintLayoutMain);


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

                // Construction d'une popup qui va permettre à l'utilisateur de choisir
                // la suppression ou non des cartes quand celles-ci sont trouvées
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_parameter_dialog, null);
                builder.setView(v);
                builder.setTitle(R.string.title_dialog_parameter);
                toggleButton = (ToggleButton) v.findViewById(R.id.togglebutton);

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
                        if (toggleButton.isChecked()) {
                            Log.e("CHECK", String.valueOf(toggleButton.getText()));
                            // SharedPreferences
                        }
                        else
                            Log.e("UNCHECKED", String.valueOf(toggleButton.getText()));
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

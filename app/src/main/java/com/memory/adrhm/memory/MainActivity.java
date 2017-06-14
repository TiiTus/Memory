package com.memory.adrhm.memory;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author : hansjulien on 29/05/2017.
 * Classe (Activité) qui représente le première page de l'application
 * utilisée pour le choix du niveau de difficulté
 */
public class MainActivity extends AppCompatActivity {

    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;

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
                // new activity
                Toast toast = Toast.makeText(this,"COUCOU",Toast.LENGTH_LONG);
                toast.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

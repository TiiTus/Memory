package com.memory.adrhm.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @author : hansjulien on 29/05/2017.
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
}

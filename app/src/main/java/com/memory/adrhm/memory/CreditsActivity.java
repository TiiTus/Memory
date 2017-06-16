package com.memory.adrhm.memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author : hansjulien on 15/06/2017.
 * Classe (Activité) qui gère l'affichage des crédits pour les photos et les images
 */

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    // Affichage de l'animation (glisse vers le bas)
    // quand l'utilisateur clique sur la flèche retour de la toolbar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.nothing, R.anim.slide_down);
        return true;
    }
}

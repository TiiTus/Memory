package com.memory.adrhm.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hansjulien on 29/05/2017.
 * Classe qui gere l'affichage des choix suivant le niveau selectionné auparavant
 */

public class SelectGameActivity extends AppCompatActivity {

    public static String titleLevel;

    private List<CardList> easyList = new ArrayList<>();
    private List<CardList> mediumList = new ArrayList<>();
    private List<CardList> hardList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);
        Intent i = getIntent();
        setTitle(getIntent().getStringExtra("level"));
        titleLevel = getIntent().getStringExtra("level");

        RecyclerView recyclerView;
        switch (titleLevel) {
            case "Facile":
                initializeEasyList();
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                // adapte la grille comme une RecyclerView, avec 2 cellules par ligne
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                recyclerView.setAdapter(new CardListAdapter(easyList));
                break;

            case "Moyen":
                initializeMediumList();
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                recyclerView.setAdapter(new CardListAdapter(mediumList));
                break;

            default:
                initializeHardList();
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                recyclerView.setAdapter(new CardListAdapter(hardList));
                break;
        }
    }

    // Remplissage des listes avec les différentes types d'images à jouer selon les niveaux
    // Image + titre

    public void initializeEasyList() {
        easyList.add(new CardList("Personnages", R.drawable.santa_claus));
        easyList.add(new CardList("Animaux", R.drawable.boar));
        easyList.add(new CardList("Drapeaux", R.drawable.france));
        //easyList.add(new CardList("Visages", R.drawable.fille_4));
        easyList.add(new CardList("Emoticones", R.drawable.smiling));
    }
    public void initializeMediumList() {
        mediumList.add(new CardList("Personnages", R.drawable.woman_10));
        mediumList.add(new CardList("Animaux", R.drawable.pig));
        mediumList.add(new CardList("Drapeaux", R.drawable.spain));
        mediumList.add(new CardList("Emoticones", R.drawable.happy_2));
    }

    public void initializeHardList() {
        hardList.add(new CardList("Personnages",R.drawable.heisenberg));
        hardList.add(new CardList("Animaux",R.drawable.cat));
        hardList.add(new CardList("Drapeaux",R.drawable.brazil));
        //hardList.add(new CardList("Visages", R.drawable.fille_4));
        //hardList.add(new CardList("Visages noir&blanc", R.drawable.fille_4_bw));
        hardList.add(new CardList("Emoticones", R.drawable.tongue_out));
    }

    public static String getTitleLevel() {
        return titleLevel;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.nothing, R.anim.slide_out);
        return true;
    }
}
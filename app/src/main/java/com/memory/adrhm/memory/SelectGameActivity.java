package com.memory.adrhm.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hansjulien on 29/05/2017.
 */

// Classe qui gere laffichage des choix suivant le niveau selectionne auparavant
public class SelectGameActivity extends AppCompatActivity {

    public static String titleLevel;

    private RecyclerView recyclerView;

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

        if (getIntent().getStringExtra("level").equals("Facile")) {

            initializeEasyList();

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            //pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

            //puis créer un CardListAdapter, lui fournir notre liste.
            //cet adapter servira à remplir notre recyclerview
            recyclerView.setAdapter(new CardListAdapter(easyList));
        }
        else if (getIntent().getStringExtra("level").equals("Moyen")) {

            initializeMediumList();

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            //pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

            //puis créer un CardListAdapter, lui fournir notre liste.
            //cet adapter servira à remplir notre recyclerview
            recyclerView.setAdapter(new CardListAdapter(mediumList));
        }
        else {
            initializeHardList();

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            //pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

            //puis créer un CardListAdapter, lui fournir notre liste.
            //cet adapter servira à remplir notre recyclerview
            recyclerView.setAdapter(new CardListAdapter(hardList));
        }
    }

    //remplir la ville
    public void initializeEasyList() {
        easyList.add(new CardList("Personnages", R.drawable.santa_claus));
        easyList.add(new CardList("Animaux", R.drawable.boar));
        easyList.add(new CardList("Drapeaux", R.drawable.france));
    }
    public void initializeMediumList() {
        mediumList.add(new CardList("Personnages", R.drawable.woman_10));
        mediumList.add(new CardList("Animaux", R.drawable.hedgehog));
        mediumList.add(new CardList("Drapeaux", R.drawable.spain));
    }
    public void initializeHardList() {
        hardList.add(new CardList("Personnages",R.drawable.heisenberg));
        hardList.add(new CardList("Animaux",R.drawable.tiger));
        hardList.add(new CardList("Drapeaux",R.drawable.brazil));
        hardList.add(new CardList("Personnages", R.drawable.woman_10));
        hardList.add(new CardList("Animaux", R.drawable.hedgehog));
        hardList.add(new CardList("Drapeaux", R.drawable.spain));
        hardList.add(new CardList("Personnages", R.drawable.santa_claus));
        hardList.add(new CardList("Animaux", R.drawable.boar));
        hardList.add(new CardList("Drapeaux", R.drawable.france));
    }

    public static String getTitleLevel() {
        return titleLevel;
    }
}
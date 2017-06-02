package com.memory.adrhm.memory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author : hansjulien on 22/05/2017.
 */

/**
 * Classe qui gere la liste de cartes
 */

public class CardListAdapter extends RecyclerView.Adapter<CardListViewHolder> {

    List<CardList> listCard;

    public CardListAdapter(List<CardList> list) {
        this.listCard = list;
    }

    //Création des viewHolder
    // et inflate la vue xml (grid_categorie_layout)
    @Override
    public CardListViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_categorie_layout,viewGroup,false);
        return new CardListViewHolder(view);
    }

    // Remplissage de la carte avec le texte/image de chaque CardList (classe)
    @Override
    public void onBindViewHolder(CardListViewHolder cardListViewHolder, int position) {
        CardList cardList = listCard.get(position);
        //cardListViewHolder.bind(cardList);
        cardListViewHolder.text.setText(listCard.get(position).text);
        cardListViewHolder.image.setImageResource(listCard.get(position).image);
    }

    @Override
    public int getItemCount() {
        return listCard.size();
    }

}
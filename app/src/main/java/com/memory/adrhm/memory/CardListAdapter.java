package com.memory.adrhm.memory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author : hansjulien on 22/05/2017.
 */

// Classe qui gere la liste de cartes
public class CardListAdapter extends RecyclerView.Adapter<CardListViewHolder> {

    List<CardList> listCard;

    //ajouter un constructeur prenant en entrée une liste
    public CardListAdapter(List<CardList> list) {
        this.listCard = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public CardListViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_categorie_layout,viewGroup,false);
        return new CardListViewHolder(view);
    }

    //remplir notre cellule avec le texte/image de chaque CardList (classe)
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
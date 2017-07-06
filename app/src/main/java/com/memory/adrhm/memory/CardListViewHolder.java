package com.memory.adrhm.memory;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author : hansjulien on 22/05/2017.
 * Classe qui garde les références vers les vues de chaque carte
 */


class CardListViewHolder extends RecyclerView.ViewHolder{

    // Titre de la carte
    TextView text;
    // Image de la carte
    ImageView image;
    private static String value;

    //itemView est la vue correspondante à 1 cellule
    CardListViewHolder(final View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.textCard);
        image = (ImageView) itemView.findViewById(R.id.imageCard);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(itemView.getContext(), GameActivity.class);
                // récupération du titre de la carte
                value = (String) text.getText();
                itemView.getContext().startActivity(i);
            }
        });
    }


    // Getters & setters

    static String getValue() {
        return value;
    }

    public void setValue(String value) {
        CardListViewHolder.value = value;
    }
}
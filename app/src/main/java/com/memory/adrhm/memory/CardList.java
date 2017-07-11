package com.memory.adrhm.memory;

/**
 * @author : hansjulien on 22/05/2017.
 * Classe qui définit ce que contient une carte dans le choix des images pour jouer
 * Une carte contient une image et un titre
  */

class CardList {
    // Titre de la carte
    String text;
    // Image de la carte
    int image;

    CardList(String text, int image) {
        this.text = text;
        this.image = image;
    }
}

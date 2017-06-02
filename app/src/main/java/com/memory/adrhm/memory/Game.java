package com.memory.adrhm.memory;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author : hansjulien on 29/05/2017.
 */

/**
 * Classe qui gère la mécanique du jeu
 * ainsi que les différentes listes d'images selon les niveaux
 */

public class Game {

    private int nbImages;

    //La liste d'images des cartes du jeu qui sont rangées dans l'ordre ou elles seront affichées.
    private Integer[] images;

    //L'état des cartes, si elles ont été trouvées ou non.
    private boolean[] revelees;

    //Variable qui permet de stocker la carte que l'on choisit en premier avant de la comparer avec une deuxième.
    private int firstClickedPosition=-1;

    /**
     * Taille des grilles selon le niveau
     *
     * Facile : 4x3 = 12 (6 images)
     * Moyen : 4x4 = 16 (8 images)
     * Difficile : 5x4 = 20 (10 images)
     * Difficile2 : 6x4 = 24 (12 images)
     */

    //Les adresses resources des images disponibles.
    public static Integer[] availableImages = {
            R.drawable.heisenberg, R.drawable.brazil,
            R.drawable.woman_10, R.drawable.tiger,
            R.drawable.santa_claus, R.drawable.boar,
    };

    /* R.drawable.spain, R.drawable.france,
            R.drawable.mario, R.drawable.hedgehog,
            R.drawable.image_11, R.drawable.image_10,
            R.drawable.mario, R.drawable.mario,
            R.drawable.mario, R.drawable.mario,
            R.drawable.mario, R.drawable.mario
    */

    //Constructeur pour nouvelle partie, on ne regarde que le nombre d'images
    public Game(int nombreImages) {

        Log.e("MEMORY-TEST", "level: " + SelectGameActivity.getTitleLevel() + "  categorie: " + CardListViewHolder.getValue());

        nbImages = nombreImages;
        // Création de deux tableaux où on double le nombre d'images
        images = new Integer[nbImages*2];
        revelees = new boolean[nbImages*2];

        // Remplissage du tableau revelees avec la valeur false initialement
        for(int i = 0; i < revelees.length; i++){
            revelees[i] = false;
        }

        // Remplissage du tableau images avec des images
        // Création d'une ArrayList avec deux fois chaque image
        ArrayList<Integer> imageArray = new ArrayList<>();
        for(int j = 0; j < nbImages; j++){
            imageArray.add(availableImages[j]);
            imageArray.add(availableImages[j]);
        }

        // Remplissage aléatoire du tableau images avec les images de l'arrayList
        for(int k = 0; k < images.length; k++) {
            Integer randomImage = imageArray.get(new Random().nextInt(imageArray.size()));
            images[k] = randomImage;
            imageArray.remove(randomImage);
        }

    }

    //Check si la carte à la position donnée est déjà retournée (càd si c'est la firstClickedCard ou si elle
    //fait partie d'une paire déjà trouvée)
    public boolean isAlreadyRevealed(int position){
        if(firstClickedPosition!=-1){
            if(firstClickedPosition == position){
                return true;
            } else{
                return (revelees[position]);
            }
        } else {
            return (revelees[position]);
        }
    }

    //Check si la carte à la position donnée et la firstClickedCard sont une paire.
    //Renvoie un boolean et met "true" dans le tableau "revelees" pour les deux cartes si
    //elles sont bonnes.
    public boolean checkForMatch(int position){
        boolean result = false;
        if(firstClickedPosition != -1){
            if(images[firstClickedPosition].equals(images[position])){
                //GOOD!
                revelees[position] = true;
                revelees[firstClickedPosition] = true;
                result = true;

            }else{
                //BAD!
                revelees[firstClickedPosition] = false;
                result = false;
            }
            firstClickedPosition=-1;
        }
        else{
            Log.d("Game", "Problème: pas de firstClickedCard");
        }
        return result;
    }

    //Check si l'utilisateur a fini la partie
    public boolean hasFinished(){
        boolean result = true;
        for(int i = 0;i<revelees.length;i++){
            if(!revelees[i]){
                result=false;
            }
        }
        return result;
    }

    public ArrayList<Integer> getImages(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<images.length;i++){
            result.add(images[i]);
        }
        return result;
    }

    // Getters & setters

    public Integer getImageAt(int position){
        return images[position];
    }

    public int getNbImages(){
        return nbImages;
    }

    public int getFirstClickedPosition(){
        return firstClickedPosition;
    }

    public void setFirstClickedPosition(int position){
        firstClickedPosition = position;
    }

    public boolean[] getRevelees(){
        return revelees;
    }
}

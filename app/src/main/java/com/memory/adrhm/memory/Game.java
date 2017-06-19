package com.memory.adrhm.memory;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author hansjulien on 29/05/2017
 * Classe qui gère la mécanique du jeu
 * ainsi que les différentes listes d'images selon les niveaux
 */

public class Game {

    private int nbImages;

    // Les images du jeu
    private Integer[] pictures;

    // A true si la carte à été trouvée (paire de cartes)
    private boolean[] returned;

    // Première carte trouvée que l'on va comparer avec la deuxième
    private int firstCard = -1;

    /**
     * Taille des grilles selon le niveau
     *
     * - 4x3 (6 pictures)
     * - 4x4 (8 pictures)
     * - 5x4 (10 pictures)
     * - (OPTIONNEL) 6x5 (15 pictures)
     * - (OPTIONNEL) 6x6 (18 pictures)
     */

    //Les adresses resources des pictures disponibles.
    private Integer[] avatarsImages = {
            R.drawable.avatar, R.drawable.avatar_1,
            R.drawable.avatar_2, R.drawable.boy,
            R.drawable.boy_1, R.drawable.boy_2,
            R.drawable.boy_3, R.drawable.boy_4,
            R.drawable.boy_5, R.drawable.catwoman,
            R.drawable.hindu, R.drawable.hindu_1,
            R.drawable.hood, R.drawable.japanese,
            R.drawable.santa_claus, R.drawable.woman_10,
            R.drawable.woman_1, R.drawable.woman_2
    };

    private Integer[] faces = {
            R.drawable.fille_1, R.drawable.fille_2,
            R.drawable.fille_3, R.drawable.fille_4,
            R.drawable.fille_5, R.drawable.fille_6,
            R.drawable.fille_7, R.drawable.homme_1,
            R.drawable.homme_2, R.drawable.homme_3,
            R.drawable.homme_4, R.drawable.homme_5,
            R.drawable.homme_6, R.drawable.homme_7,


    };

    private Integer[] facesBW = {
            R.drawable.fille_1_bw, R.drawable.fille_2_bw,
            R.drawable.fille_3_bw, R.drawable.fille_4_bw,
            R.drawable.fille_5_bw, R.drawable.fille_6_bw,
            R.drawable.fille_7_bw, R.drawable.homme_1_bw,
            R.drawable.homme_2_bw, R.drawable.homme_3_bw,
            R.drawable.homme_4_bw, R.drawable.homme_5_bw,
            R.drawable.homme_6_bw, R.drawable.homme_7_bw,
            R.drawable.fille_4_bw
    };

    private Integer[] flagsImages = {
            R.drawable.spain, R.drawable.brazil,
            R.drawable.france, R.drawable.brazil,
            R.drawable.france, R.drawable.spain,
            R.drawable.brazil, R.drawable.spain
    };

    private Integer[] mediumImages = {
            R.drawable.heisenberg, R.drawable.brazil,
            R.drawable.woman_10, R.drawable.tiger,
            R.drawable.santa_claus, R.drawable.boar,
            R.drawable.spain, R.drawable.france,
            R.drawable.woman_2, R.drawable.hedgehog,
    };

    // Constructeur d'instanciation d'une nouvelle partie
    public Game(int nombreImages) {

        Log.e("MEMORY-TEST", "level: " + SelectGameActivity.getTitleLevel() + "  categorie: " + CardListViewHolder.getValue());

        nbImages = nombreImages;
        // On double le nombre d'pictures pour avoir des paires
        pictures = new Integer[nbImages*2];
        returned = new boolean[nbImages*2];

        // On initialise le tableau returned à false au départ
        for(int i = 0; i < returned.length; i++){
            returned[i] = false;
        }

        String categorie = CardListViewHolder.getValue();

        List<Integer> imageArray = new ArrayList<>();
        for(int j = 0; j < nbImages; j++){
            //int im = new Random().nextInt(avatarsImages.length);

            switch (SelectGameActivity.getTitleLevel()) {
                case "Facile":
                    if (categorie.equals("Animaux")) {
                        imageArray.add(avatarsImages[j]);
                        imageArray.add(avatarsImages[j]);
                    }
                    else if (categorie.equals("Personnages")) {
                        imageArray.add(avatarsImages[j]);
                        imageArray.add(avatarsImages[j]);
                    }
                    else if (categorie.equals("Drapeaux")) {
                        imageArray.add(avatarsImages[j]);
                        imageArray.add(avatarsImages[j]);
                    }
                    break;
                case "Moyen":
                    imageArray.add(mediumImages[j]);
                    imageArray.add(mediumImages[j]);
                    break;
                case "Difficile":
                    imageArray.add(facesBW[j]);
                    imageArray.add(facesBW[j]);
                    break;
            }
        }

        // Remplissage aléatoire du tableau pictures avec les pictures de l'arrayList
        for(int k = 0; k < pictures.length; k++) {
            Integer randomImage = imageArray.get(new Random().nextInt(imageArray.size()));
            pictures[k] = randomImage;
            imageArray.remove(randomImage);
        }
    }

    /*private void getCategorie(int num) {
        ArrayList<Integer> imageArray = new ArrayList<>();
        switch (CardListViewHolder.getValue()) {
            case "Personnages" :

            case "Drapeaux" :
                imageArray.add(flagImages[num]);
                imageArray.add(flagImages[num]);

        }
    }*/

    // Méthode qui vérifie si la carte (position) est déjà retournée
    public boolean isAlreadyReturned(int position){
        if(firstCard != -1) {
            return firstCard == position || (returned[position]);
        } else {
            return (returned[position]);
        }
    }

    // Méthode qui vérifie si la firstCard correspond à l'autre card
    public boolean check(int position){
        boolean result = false;
        if(firstCard != -1){
            if(pictures[firstCard].equals(pictures[position])){
                // Trouvé
                returned[position] = true;
                returned[firstCard] = true;
                result = true;

            }else{
                // Non trouvé
                returned[firstCard] = false;
                result = false;
            }
            firstCard =-1;
        }
        return result;
    }

    // Méthode qui vérifie si l'utilisateur à fini la partie
    public boolean finishedGame(){
        boolean result = true;
        for(int i = 0; i< returned.length; i++){
            if(!returned[i]){
                result=false;
            }
        }
        return result;
    }

    // Getters & setters

    public Integer getImageAt(int position){
        return pictures[position];
    }

    public  int getNbImages(){
        return nbImages;
    }

    public int getFirstCard(){
        return firstCard;
    }

    public void setFirstCard(int position){
        firstCard = position;
    }

    public boolean[] getReturned(){
        return returned;
    }


    public ArrayList<Integer> getPictures(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i< pictures.length; i++){
            result.add(pictures[i]);
        }
        return result;
    }
}

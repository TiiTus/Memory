package com.memory.adrhm.memory;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * @author hansjulien on 29/05/2017
 * Classe qui gère la mécanique du jeu
 * ainsi que les différentes listes d'images selon les niveaux
 */

public class Game {

    // Nombre d'images (sans paires)
    private int nbImages;
    // Les images du jeu
    private Integer[] pictures;
    // A true si la carte à été trouvée (paire de cartes)
    private boolean[] returned;
    // Première carte retournée que l'on va comparer avec la deuxième
    private int firstCard = -1;
    // Liste qui contient les images de la catégorie choisie et mélangées
    private List<Integer> shuffle;

    /**
     * Taille des grilles selon le niveau
     *
     * - 4x4 (8 images)
     * - 5x4 (10 images)
     * - 6x5 (15 images)
     */

    //Les adresses resources des images disponibles
    private Integer[] avatarsImages = {
            R.drawable.avatar_1, R.drawable.avatar_2,
            R.drawable.boy_1, R.drawable.boy_2,
            R.drawable.boy_3, R.drawable.boy_4,
            R.drawable.boy_5, R.drawable.heisenberg,
            R.drawable.hindu, R.drawable.man_1,
            R.drawable.man_2, R.drawable.man,
            R.drawable.santa_claus, R.drawable.woman_1,
            R.drawable.woman_2, R.drawable.woman_3,
            R.drawable.woman_4, R.drawable.woman_8,
            R.drawable.woman_9, R.drawable.woman_10,
            R.drawable.woman_13, R.drawable.woman_14,
            R.drawable.woman_18, R.drawable.woman
    };

    private Integer[] animals = {
            R.drawable.bee, R.drawable.boar,
            R.drawable.bunny, R.drawable.cat,
            R.drawable.cow, R.drawable.donkey,
            R.drawable.elephant, R.drawable.hedgehog,
            R.drawable.hen, R.drawable.hippopotamus,
            R.drawable.kangaroo, R.drawable.koala,
            R.drawable.ladybug, R.drawable.lion,
            R.drawable.monkey, R.drawable.mouse,
            R.drawable.octopus, R.drawable.owl,
            R.drawable.panda, R.drawable.penguin,
            R.drawable.pig, R.drawable.sheep,
            R.drawable.snail, R.drawable.wolf,
            R.drawable.zebra
    };

    private Integer[] emoji = {
            R.drawable.angry, R.drawable.bored,
            R.drawable.confused, R.drawable.crying,
            R.drawable.happy_1, R.drawable.happy_2,
            R.drawable.ill, R.drawable.in_love,
            R.drawable.kissing, R.drawable.mad,
            R.drawable.nerd, R.drawable.quiet,
            R.drawable.sad, R.drawable.smiling,
            R.drawable.surprised, R.drawable.suspicious,
            R.drawable.tongue_out, R.drawable.unhappy,
            R.drawable.whistle
    };

    private Integer[] faces = {
            R.drawable.fille_1, R.drawable.fille_2,
            R.drawable.fille_3, R.drawable.fille_4,
            R.drawable.fille_5, R.drawable.fille_6,
            R.drawable.fille_7, R.drawable.fille_8,
            R.drawable.homme_1, R.drawable.homme_2,
            R.drawable.homme_3, R.drawable.homme_4,
            R.drawable.homme_5, R.drawable.homme_6,
            R.drawable.homme_7


    };

    private Integer[] facesBW = {
            R.drawable.fille_1_bw, R.drawable.fille_2_bw,
            R.drawable.fille_3_bw, R.drawable.fille_4_bw,
            R.drawable.fille_5_bw, R.drawable.fille_6_bw,
            R.drawable.fille_7_bw, R.drawable.fille_8_bw,
            R.drawable.homme_1_bw, R.drawable.homme_2_bw,
            R.drawable.homme_3_bw, R.drawable.homme_4_bw,
            R.drawable.homme_5_bw, R.drawable.homme_6_bw,
            R.drawable.homme_7_bw
    };

    private Integer[] flagsImages = {
            R.drawable.austria, R.drawable.brazil,
            R.drawable.estonia, R.drawable.france,
            R.drawable.italy, R.drawable.wales,
            R.drawable.luxembourg, R.drawable.spain,
            R.drawable.ghana, R.drawable.scotland,
            R.drawable.japan, R.drawable.lithuania,
            R.drawable.finland, R.drawable.germany,
            R.drawable.india

    };

    // Constructeur d'instanciation d'une nouvelle partie
    public Game(int nombreImages) {

        Log.e("MEMORY-TEST", "level: " + SelectGameActivity.getTitleLevel() + "  categorie: " + CardListViewHolder.getValue());

        nbImages = nombreImages;
        // On double le nombre d'images pour avoir des paires
        pictures = new Integer[nbImages*2];
        returned = new boolean[nbImages*2];

        // On initialise le tableau returned à false au départ
        for(int i = 0; i < returned.length; i++){
            returned[i] = false;
        }

        // Récupération de la catégorie choisie
        String categorie = CardListViewHolder.getValue();

        // Remplissage de la liste shuffle avec le tableau d'images correspondantes à la catégorie choisie,
        // puis on mélange pour ne pas toujours jouer avec les mêmes images (x premières images du tableau)
        switch (categorie) {
            case "Animaux":
                shuffle = Arrays.asList(animals);
                Collections.shuffle(shuffle);
                break;

            case "Personnages":
                shuffle = Arrays.asList(avatarsImages);
                Collections.shuffle(shuffle);
                break;

            case "Emoticones":
                shuffle = Arrays.asList(emoji);
                Collections.shuffle(shuffle);
                break;

            case "Drapeaux":
                shuffle = Arrays.asList(flagsImages);
                Collections.shuffle(shuffle);
                break;

            case "Visages":
                shuffle = Arrays.asList(faces);
                Collections.shuffle(shuffle);
                break;

            case "Visages noir&blanc":
                shuffle = Arrays.asList(facesBW);
                Collections.shuffle(shuffle);
                break;
        }

        // On ajoute 2 fois les images dans la liste imageArray pour obtenir des paires
        // Selon le niveau de difficulté choisi
        List<Integer> imageArray = new ArrayList<>();
        for(int j = 0; j < nbImages; j++){

            switch (SelectGameActivity.getTitleLevel()) {
                case "Facile":
                    imageArray.add(shuffle.get(j));
                    imageArray.add(shuffle.get(j));
                    break;

                case "Moyen":
                    imageArray.add(shuffle.get(j));
                    imageArray.add(shuffle.get(j));
                    break;

                case "Difficile":
                    imageArray.add(shuffle.get(j));
                    imageArray.add(shuffle.get(j));
                    break;
            }
        }

        // Remplissage aléatoire du tableau pictures avec les images de l'arrayList
        for(int k = 0; k < pictures.length; k++) {
            Integer randomImage = imageArray.get(new Random().nextInt(imageArray.size()));
            pictures[k] = randomImage;
            imageArray.remove(randomImage);
        }
    }


    // Méthode qui vérifie si la carte (position) est déjà retournée
    boolean isAlreadyReturned(int position){
        if(firstCard != -1) {
            return firstCard == position || (returned[position]);
        } else {
            return (returned[position]);
        }
    }

    // Méthode qui vérifie si la firstCard correspond à la deuxième carte cliquée
    boolean check(int position){
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
    boolean finishedGame(){
        boolean result = true;
        for (boolean aReturned : returned) {
            if (!aReturned) {
                result = false;
            }
        }
        return result;
    }

    // Getters & setters

    Integer getImageAt(int position){
        return pictures[position];
    }

    int getNbImages(){
        return nbImages;
    }

    int getFirstCard(){
        return firstCard;
    }

    void setFirstCard(int position){
        firstCard = position;
    }
}

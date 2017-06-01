package com.memory.adrhm.memory;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author : hansjulien on 29/05/2017.
 */

public class Game {

    //Le nombre d'images (indique la difficulté) de la partie.
    private int nbImages;

    //Le timer propre au Game. Il n'est updaté que quand on a besoin de sauvegarder la partie ou quand
    //on charge une partie à partir d'un Game.
    private int timerMs;

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
    public Game(int nombreImages){
        /* ** TEST ** */
        Log.e("MEMORY-TEST", "level: " + SelectGameActivity.getTitleLevel() + "  categorie: " + CardListViewHolder.getValue());

        //On créé deux tableaux avec nombreImagesx2 cases
        nbImages = nombreImages;
        images = new Integer[nbImages*2];
        revelees = new boolean[nbImages*2];

        //On remplit le tableau revelees avec que des false pour l'instant.
        for(int i=0;i<revelees.length;i++){
            revelees[i] = false;
        }

        //Fill the images with randomly generated images
        //For that, create an arrayList with twice each image:
        //On remplit celui des images avec des images générées aléatoirement.
        //Pour cela, on créé une ArrayList avec deux fois chaque image
        ArrayList<Integer> imageArray = new ArrayList<>();
        for(int j=0;j<nbImages;j++){
            imageArray.add(availableImages[j]);
            imageArray.add(availableImages[j]);
        }

        //on remplit le tableau images avec les images de l'arrayList, de manière aléatoire.
        for(int k = 0;k<images.length;k++) {
            Integer randomImage = imageArray.get(new Random().nextInt(imageArray.size()));
            images[k] = randomImage;
            imageArray.remove(randomImage);
        }

    }

    //Constructeur utilisé quand l'utilisateur à changé l'orientation de son écran.
    //Il est différent du suivant car la Bundle des onSaveInstanceState n'accepte que des array list pour les Integer.
    public Game(int nombreImages, ArrayList<Integer> imagesSaved, boolean[] reveleesSaved, int time){
        nbImages = nombreImages;
        images = new Integer[nbImages*2];
        revelees = new boolean[nbImages*2];
        for(int i = 0;i<reveleesSaved.length;i++){
            images[i] = imagesSaved.get(i);
        }
        revelees = reveleesSaved;
        timerMs = time*1000;
    }

    //Même que précedement mais pour quand l'utilisateur charge une partie précedente.
    public Game(int nombreImages, Integer[] imagesSaved, boolean[] reveleesSaved, int time){
        nbImages = nombreImages;
        images = imagesSaved;
        revelees = reveleesSaved;
        timerMs = time*1000;
    }

    //Renvoie l'adresse resource de l'image à la position donnée.
    public Integer getImageAt(int position){
        return images[position];
    }

    //Renvoie le nombre d'images
    public int getNbImages(){
        return nbImages;
    }

    //Renvoie la position de la firstClickedCard
    public int getFirstClickedPosition(){
        return firstClickedPosition;
    }

    //Set la position de la firstClickedCard
    public void setFirstClickedPosition(int position){
        firstClickedPosition = position;
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

    //Renvoie l'arrayList des adresse resource des images de cette partie
    public ArrayList<Integer> getImages(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<images.length;i++){
            result.add(images[i]);
        }
        return result;
    }

    //Renvoie l'état des cartes
    public boolean[] getRevelees(){
        return revelees;
    }

    //Renvoie le timer
    public int getTimer(){
        return timerMs;
    }
}

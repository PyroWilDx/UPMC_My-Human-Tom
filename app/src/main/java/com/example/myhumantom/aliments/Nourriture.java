package com.example.myhumantom.aliments;

import android.content.Context;

import com.example.myhumantom.joueur.Sauvegardable;

public abstract class Nourriture implements Sauvegardable {
    private final String nom;
    private final int prix;
    private final int image;
    private Nutriments nutriments;

    public Nourriture(String nom, int prix, int image, Nutriments nutriments) {
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.nutriments = nutriments;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    public int getImage() {
        return image;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public abstract Nourriture clone();

    public String toString() {
        return nom + " : " + prix + "$" + nutriments.toString();
    }

    @Override
    public void sauvegarder(Context context) {}
}

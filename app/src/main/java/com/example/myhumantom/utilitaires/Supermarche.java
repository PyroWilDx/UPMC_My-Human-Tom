package com.example.myhumantom.utilitaires;

import com.example.myhumantom.joueur.Joueur;
import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.aliments.fruits.Banane;
import com.example.myhumantom.aliments.fruits.Pomme;
import com.example.myhumantom.aliments.legumes.Carotte;
import com.example.myhumantom.aliments.legumes.Salade;
import com.example.myhumantom.aliments.viandes.Steak;
import com.example.myhumantom.aliments.viandes.Porc;
import com.example.myhumantom.aliments.viandes.Poulet;

public class Supermarche {

    public static final Nourriture[] STOCK = {
            new Banane(),
            new Pomme(),
            new Carotte(),
            new Salade(),
            new Steak(),
            new Porc(),
            new Poulet(),
    };

    public static int vendre(int position) {
        Joueur joueur = Joueur.getInstance();
        Nourriture nourriture = STOCK[position].clone();
        if (joueur.getArgent() - nourriture.getPrix() < 0) {
            return 0;
        }
        if (joueur.getInventaire().add(nourriture)) {
            enleverArgentJouer(nourriture.getPrix());
            return 1;
        }
        return -1;
    }

    public static void enleverArgentJouer(int valeur) {
        Joueur joueur = Joueur.getInstance();
        joueur.setArgent(joueur.getArgent() - valeur);
    }

    public static String[] getTabStockNomPrix() {
        String[] res = new String[STOCK.length];
        for (int i = 0; i < STOCK.length; i++) {
            res[i] = STOCK[i].getNom() + ", " + STOCK[i].getPrix() + "$";
        }
        return res;
    }

    public static int[] getTabStockImage() {
        int[] res = new int[STOCK.length];
        for (int i = 0; i < STOCK.length; i++) {
            res[i] = STOCK[i].getImage();
        }
        return res;
    }

}

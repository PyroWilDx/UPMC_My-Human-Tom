package com.example.myhumantom.joueur;

import android.content.Context;

import com.example.myhumantom.aliments.Nutriments;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FichiersJoueur {
    public static final String NOM_FICHIER_JOUEUR = "stats.txt";
    public static final String NOM_FICHIER_NUTRIMENTS = "nutriments.txt";
    public static final String NOM_FICHIER_INVENTAIRE = "inventaire.txt";

    private FichiersJoueur() {}

    public static void suppFichiers(Context context) {
        context.deleteFile(NOM_FICHIER_JOUEUR);
        context.deleteFile(NOM_FICHIER_NUTRIMENTS);
        context.deleteFile(NOM_FICHIER_INVENTAIRE);
    }

    public static void sauvegardeAll(Context context) throws IOException {
        suppFichiers(context);
        Joueur joueur = Joueur.getInstance();
        joueur.sauvegarder(context);
        joueur.getNutriments().sauvegarder(context);
        joueur.getInventaire().sauvegarder(context);
    }

    public static int[] lireStats(Context context) throws IOException {
        FileInputStream fis = context.openFileInput(NOM_FICHIER_JOUEUR);
        DataInputStream reader = new DataInputStream(fis);
        int[] res = new int[4];
        for (int i = 0; i < res.length; i++) {
            res[i] = reader.readInt();
        }
        reader.close();
        return res;
    }

    public static Nutriments lireNutriments(Context context) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(NOM_FICHIER_NUTRIMENTS);
        ObjectInputStream reader = new ObjectInputStream(fis);
        Nutriments res = (Nutriments) reader.readObject();
        reader.close();
        return res;
    }

    public static Inventaire lireInventaire(Context context) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(NOM_FICHIER_INVENTAIRE);
        ObjectInputStream reader = new ObjectInputStream(fis);
        Inventaire res = (Inventaire) reader.readObject();
        reader.close();
        return res;
    }

    public static void initJoueur(Context context) throws IOException, ClassNotFoundException {
        int[] stats = lireStats(context);
        Nutriments nutriments = lireNutriments(context);
        Inventaire inventaire = lireInventaire(context);
        Joueur.getInstance().init(
                stats[0],
                stats[1],
                stats[2],
                stats[3],
                nutriments,
                inventaire);
    }

}

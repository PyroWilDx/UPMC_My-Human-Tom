package com.example.myhumantom.aliments;

import android.content.Context;

import com.example.myhumantom.joueur.FichiersJoueur;
import com.example.myhumantom.joueur.Joueur;
import com.example.myhumantom.joueur.Sauvegardable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Nutriments implements Sauvegardable {

    public static final String[] NOM_NUTRIMENTS_TAB = {
            "Calories", "Lipides", "Protéines",
            "Surcres", "Sodiums", "Fibres"
    };
    public static final String[] UNITE_NUTRIMENTS_TAB = {
            "kcal", "g", "g",
            "g", "mg", "g"
    };

    public static final int[][] INTERVALLE_NUTRIMENTS = {
            {2000, 3000},
            {50, 110},
            {60, 120},
            {30, 70},
            {400, 900},
            {10, 24}
    };

    private double[] tabNutri;

    public Nutriments(double[] tabNutri) {
        this.tabNutri = new double[NOM_NUTRIMENTS_TAB.length];
        for (int i = 0; i < this.tabNutri.length; i++) {
            this.tabNutri[i] = tabNutri[i];
        }
    }

    public void addNutriments(Nutriments nutriments) {
        for (int i = 0; i < tabNutri.length; i++) {
            tabNutri[i] += nutriments.tabNutri[i];
        }
    }

    public void modifNutrimentsCoeff(double coeff) {
        for (int i = 0; i < tabNutri.length; i++) {
            tabNutri[i] *= coeff;
        }
    }

    public void setTabNutri(double[] tabNutri) {
        this.tabNutri = tabNutri;
    }

    public double[] getTabNutri() {
        return tabNutri;
    }

    public static String intervallesToString() {
        String res = "";
        for (int i = 0; i < INTERVALLE_NUTRIMENTS.length; i++) {
            res += NOM_NUTRIMENTS_TAB[i] + " : " + "[" + INTERVALLE_NUTRIMENTS[i][0]
                    + ", " + INTERVALLE_NUTRIMENTS[i][1] + "]\n";
        }
        return res;
    }

    public String toStringJoueur() {
        String res = "";
        double[] temp = Joueur.getInstance().getNutriments().tabNutri;
        for (int i = 0; i < tabNutri.length; i++) {
            if (temp[i] >= INTERVALLE_NUTRIMENTS[i][0] &&
                    temp[i] <= INTERVALLE_NUTRIMENTS[i][1]) {
                res += " ✔ ";
            } else {
                res += " ✘ ";
            }
            res +=  NOM_NUTRIMENTS_TAB[i] + " : " +
                    String.format("%.1f", tabNutri[i]) + " " +
                    UNITE_NUTRIMENTS_TAB[i] + ", [" +
                    INTERVALLE_NUTRIMENTS[i][0] + ", " +
                    INTERVALLE_NUTRIMENTS[i][1] + "]" + "\n";
        }
        return res;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < tabNutri.length; i++) {
            res += " - " + NOM_NUTRIMENTS_TAB[i] + " : " +
                    String.format("%.1f", tabNutri[i]) + " " +
                    UNITE_NUTRIMENTS_TAB[i] + "\n";
        }
        return res;
    }

    @Override
    public void sauvegarder(Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput(FichiersJoueur.NOM_FICHIER_NUTRIMENTS, Context.MODE_PRIVATE);
        ObjectOutputStream writer = new ObjectOutputStream(fos);
        writer.writeObject(this);
        writer.close();
    }
}

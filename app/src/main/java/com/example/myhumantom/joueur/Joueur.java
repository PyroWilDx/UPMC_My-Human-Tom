package com.example.myhumantom.joueur;

import android.content.Context;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.aliments.Plat;
import com.example.myhumantom.exceptions.DejaCuitException;
import com.example.myhumantom.exceptions.NonComposableException;
import com.example.myhumantom.exceptions.NonCuisinableException;
import com.example.myhumantom.aliments.interfaces.Composable;
import com.example.myhumantom.utilitaires.Constantes;
import com.example.myhumantom.aliments.interfaces.Cuisinable;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Joueur implements Sauvegardable {
    private static final Joueur INSTANCE = new Joueur();

    private int level;
    private int exp;
    private int argent;
    private int momentDuJour; // 0 = Matin, 1 = Midi, 2 = Soir
    private Nutriments nutriments;
    private Inventaire inventaire;

    public static Joueur getInstance() {
        return INSTANCE;
    }

    private Joueur() {
    }

    public void init(int level, int exp, int argent, int momentDuJour,
                     Nutriments nutriments, Inventaire inventaire) {
        this.level = level;
        this.exp = exp;
        this.argent = argent;
        this.momentDuJour = momentDuJour;
        this.nutriments = nutriments;
        this.inventaire = inventaire;
    }

    public void reset() {
        init(1,
                0,
                Constantes.BASE_ARGENT,
                0,
                new Nutriments(Constantes.BASE_TAB_NUTRI_VALEURS.clone()),
                new Inventaire());
    }

    public void manger(int position) { //position est la position de la nourriture dans l'inventaire
        Nourriture nourriture = inventaire.getNourritureFromPosition(position);
        inventaire.remove(position);
        nutriments.addNutriments(nourriture.getNutriments());
        addExp(Constantes.GAIN_EXP_MANGER);
    }

    public void consommeNutriments(double coeff) {
        nutriments.modifNutrimentsCoeff(coeff);
    }

    public void travailler() {
        addExp(Constantes.GAIN_EXP_TRAVAIL);
        consommeNutriments(Constantes.COEFF_PERTE_NUTRI_TRAVAIL);
        if (momentDuJour == 2) {
            dormir();
        }
        avancerMomentDuJour();
    }

    public void dormir() {
        consommeNutriments(Constantes.COEFF_PERTE_NUTRI_DORMIR);
    }

    public void cuisiner(int position) throws NonCuisinableException, DejaCuitException {
        Nourriture nourriture = inventaire.getNourritureFromPosition(position);

        if (nourriture != null) {
            if (nourriture instanceof Cuisinable) {
                Cuisinable cuisinable = (Cuisinable) nourriture;
                cuisinable.cuire();
                addExp(Constantes.GAIN_EXP_CUISINE);
                consommeNutriments(Constantes.COEFF_PERTE_NUTRI_CUISINE);
            } else {
                throw new NonCuisinableException();
            }
        }
    }

    public void composer(int position1, int position2) throws NonComposableException {
        Nourriture nourriture1 = inventaire.getNourritureFromPosition(position1);
        Nourriture nourriture2 = inventaire.getNourritureFromPosition(position2);

        if (nourriture1 != null && nourriture2 != null) {
            if (!(nourriture1 instanceof Composable || nourriture2 instanceof Composable)) {
                throw new NonComposableException(nourriture1, nourriture2);
            } else if (!(nourriture1 instanceof Composable)) {
                throw new NonComposableException(nourriture1);
            } else if (!(nourriture2 instanceof Composable)) {
                throw new NonComposableException(nourriture2);
            } else {
                Composable composable1 = (Composable) nourriture1;
                Composable composable2 = (Composable) nourriture2;
                Plat plat = composable1.composer(composable2);

                inventaire.remove(position1);
                if (position2 > position1) {
                    inventaire.remove(position2 - 1); //toutes nourritures ont été décalées
                } else {
                    inventaire.remove(position2);
                }
                inventaire.add(plat);

                addExp(Constantes.GAIN_EXP_COMPOSE);
                consommeNutriments(Constantes.COEFF_PERTE_NUTRI_COMPOSE);
            }
        }
    }

    public boolean peutTravailler() {
        for (int i = 0; i < nutriments.getTabNutri().length; i++) {
            if (nutriments.getTabNutri()[i] >= Nutriments.INTERVALLE_NUTRIMENTS[i][0]) {
                return true;
            }
        }
        return false;
    }

    public boolean estMort() {
        return argent < Constantes.MINIMUM_ARGENT && inventaire.getCpt() == 0;
    }

    public int coutCheckUpHopital() {
        int res = 0;
        for (int i = 0; i < nutriments.getTabNutri().length; i++) {
            if (nutriments.getTabNutri()[i] < Nutriments.INTERVALLE_NUTRIMENTS[i][0] ||
                    nutriments.getTabNutri()[i] > Nutriments.INTERVALLE_NUTRIMENTS[i][1]) {
                if (argent - Constantes.COUT_OPERATION_HOPITAL >= 0) {
                    argent -= Constantes.COUT_OPERATION_HOPITAL;
                    res += Constantes.COUT_OPERATION_HOPITAL;
                    nutriments.getTabNutri()[i] = (Nutriments.INTERVALLE_NUTRIMENTS[i][0] +
                            Nutriments.INTERVALLE_NUTRIMENTS[i][1]) / 2.0;
                }
            }
        }
        return res;
    }

    public void levelUp() {
        level++;
    }

    public void addExp(int valeur) {
        exp += valeur;
        while (exp >= level * Constantes.COEFF_LEVEL) {
            levelUp();
            exp = exp - ((level - 1) * Constantes.COEFF_LEVEL);
        }
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getArgent() {
        return argent;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void avancerMomentDuJour() {
        momentDuJour = (momentDuJour + 1) % 3;
    }

    public String getMomentDuJourString() {
        String[] temp = {"Matin", "Midi", "Soir"};
        return temp[momentDuJour];
    }

    @Override
    public String toString() {
        String res = "";
        res += "Niveau : " + level + "\n";
        res += "Expérience : " + exp + "/" + (level * Constantes.COEFF_LEVEL) + "\n";
        res += "Argent : " + argent + "$" + "\n";
        res += "Moment : " + getMomentDuJourString() + "\n";
        res += "Nutriments (énergie) : " + "\n" + nutriments.toStringJoueur();
        res += "Inventaire : " + inventaire.toString();
        return res;
    }

    @Override
    public void sauvegarder(Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput(FichiersJoueur.NOM_FICHIER_JOUEUR, Context.MODE_PRIVATE);
        DataOutputStream writer = new DataOutputStream(fos);
        writer.writeInt(level);
        writer.writeInt(exp);
        writer.writeInt(argent);
        writer.writeInt(momentDuJour);
        writer.close();
    }
}

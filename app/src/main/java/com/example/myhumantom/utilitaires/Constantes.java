package com.example.myhumantom.utilitaires;

public class Constantes {

    private Constantes() {}

    public static final double COEFF_CUIRE_VIANDE = 1.25;
    public static final double COEFF_CUIRE_LEGUME = 1.15;
    public static final double COEFF_COMPOSER = 1.2;
    public static final double COEFF_PENALITE_COMPOSER = 0.75; //si le joueur compose de la viande avec la viande par ex.

    public static final int BASE_ARGENT = 200;
    public static final double[] BASE_TAB_NUTRI_VALEURS = {
            2600,
            90,
            100,
            55,
            800,
            20
    };

    public static final int COEFF_LEVEL = 6; //nombre d'exp qu'il faut par rapport au niveau
    //i.e : il faut (level * COEFF_LEVEL) pour monter d'un niveau.

    public static final int GAIN_EXP_TRAVAIL = 16;
    public static final int GAIN_EXP_MANGER = 1;
    public static final int GAIN_EXP_CUISINE = 6;
    public static final int GAIN_EXP_COMPOSE = 8;

    public static final double COEFF_PERTE_NUTRI_TRAVAIL = 0.85;
    public static final double COEFF_PERTE_NUTRI_DORMIR = 0.4;
    public static final double COEFF_PERTE_NUTRI_CUISINE = 0.975;
    public static final double COEFF_PERTE_NUTRI_COMPOSE = 0.975;

    public static final int MINIMUM_ARGENT = 10;
    public static final int COUT_OPERATION_HOPITAL = 20;
}

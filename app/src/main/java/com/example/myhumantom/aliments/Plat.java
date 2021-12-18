package com.example.myhumantom.aliments;

import com.example.myhumantom.R;
import com.example.myhumantom.utilitaires.Constantes;

public class Plat extends Nourriture {

    private final Nourriture n1;
    private final Nourriture n2;
    private final boolean sontPareils;

    public Plat(Nourriture n1, Nourriture n2, boolean sontPareils) {
        super("Plat : " + n1.getNom() + ", " + n2.getNom(),
                0,
                R.drawable.plat,
                new Nutriments(new double[]{0, 0, 0, 0, 0, 0}));
        this.n1 = n1;
        this.n2 = n2;
        this.sontPareils = sontPareils;
        this.setNutriments();
    }

    public void setNutriments() {
        double[] tabNutri = new double[Nutriments.NOM_NUTRIMENTS_TAB.length];
        for (int i = 0; i < tabNutri.length; i++) {
            tabNutri[i] = n1.getNutriments().getTabNutri()[i]
                    + n2.getNutriments().getTabNutri()[i];
        }
        getNutriments().setTabNutri(tabNutri);
        getNutriments().modifNutrimentsCoeff(Constantes.COEFF_COMPOSER);
        if (sontPareils) {
            getNutriments().modifNutrimentsCoeff(Constantes.COEFF_PENALITE_COMPOSER);
        }
    }

    @Override
    public Nourriture clone() {
        return null;
    }
}

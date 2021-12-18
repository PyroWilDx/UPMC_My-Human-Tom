package com.example.myhumantom.aliments.legumes;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.R;
import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.exceptions.DejaCuitException;
import com.example.myhumantom.utilitaires.Constantes;
import com.example.myhumantom.aliments.interfaces.Cuisinable;

public class Carotte extends Legume implements Cuisinable {
    private boolean cuit;

    public Carotte() {
        super("Carotte",
                12,
                R.drawable.carotte,
                new Nutriments(new double[]{50.25, 5.923, 0.7875,
                        7.5, 86.25, 3.375})
        );
    }

    public void cuire() throws DejaCuitException {
        if (!cuit) {
            getNutriments().modifNutrimentsCoeff(Constantes.COEFF_CUIRE_LEGUME);
            cuit = true;
        } else {
            throw new DejaCuitException();
        }
    }

    @Override
    public String getNom() {
        if (cuit) {
            return super.getNom() + " (Cuite)";
        }
        return super.getNom() + " (Crue)";
    }

    @Override
    public Nourriture clone() {
        return new Carotte();
    }
}

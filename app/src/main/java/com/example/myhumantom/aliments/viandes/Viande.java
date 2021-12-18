package com.example.myhumantom.aliments.viandes;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.aliments.Plat;
import com.example.myhumantom.exceptions.DejaCuitException;
import com.example.myhumantom.aliments.interfaces.Composable;
import com.example.myhumantom.utilitaires.Constantes;
import com.example.myhumantom.aliments.interfaces.Cuisinable;

public abstract class Viande extends Nourriture implements Cuisinable, Composable {
    private boolean cuit;

    public Viande(String nom, int prix, int image, Nutriments nutriment) {
        super(nom, prix, image, nutriment);
        cuit = false;
    }

    public void cuire() throws DejaCuitException {
        if (!cuit) {
            getNutriments().modifNutrimentsCoeff(Constantes.COEFF_CUIRE_VIANDE);
            cuit = true;
        } else {
            throw new DejaCuitException();
        }
    }

    public Plat composer(Composable composable) {
        return new Plat(this, (Nourriture) composable,
                composable instanceof Viande);
    }

    @Override
    public String getNom() {
        if (cuit) {
            return super.getNom() + " (Cuit)";
        }
        return super.getNom() + " (Cru)";
    }

}
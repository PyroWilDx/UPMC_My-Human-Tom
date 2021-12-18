package com.example.myhumantom.aliments.legumes;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.aliments.Plat;
import com.example.myhumantom.aliments.interfaces.Composable;

public abstract class Legume extends Nourriture implements Composable {

    public Legume(String nom, int prix, int image, Nutriments nutriment) {
        super(nom, prix, image, nutriment);
    }

    public Plat composer(Composable composable) {
        return new Plat(this, (Nourriture) composable,
                composable instanceof Legume);
    }
}

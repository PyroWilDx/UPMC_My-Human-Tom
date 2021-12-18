package com.example.myhumantom.aliments.fruits;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.aliments.Nourriture;

public abstract class Fruit extends Nourriture {

    public Fruit(String nom, int prix, int image, Nutriments nutriment) {
        super(nom, prix, image, nutriment);
    }

}

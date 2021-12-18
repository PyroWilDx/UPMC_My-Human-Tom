package com.example.myhumantom.aliments.viandes;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.R;
import com.example.myhumantom.aliments.Nourriture;

public class Porc extends Viande {

    public Porc() {
        super("Porc",
                60,
                R.drawable.porc,
                new Nutriments(new double[]{676, 28, 37,
                        0, 167.2, 0})
        );
    }

    @Override
    public Nourriture clone() {
        return new Porc();
    }
}
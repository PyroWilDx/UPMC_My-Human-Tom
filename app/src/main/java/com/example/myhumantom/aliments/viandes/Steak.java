package com.example.myhumantom.aliments.viandes;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.R;
import com.example.myhumantom.aliments.Nourriture;

public class Steak extends Viande {

    public Steak() {
        super("Steak",
                40,
                R.drawable.steak,
                new Nutriments(new double[]{484, 23.75, 31.25,
                        0, 231.25, 0})
        );
    }

    @Override
    public Nourriture clone() {
        return new Steak();
    }
}
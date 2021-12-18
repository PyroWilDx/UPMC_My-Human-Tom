package com.example.myhumantom.aliments.fruits;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.R;
import com.example.myhumantom.aliments.Nourriture;

public class Pomme extends Fruit {

    public Pomme() {
        super("Pomme",
                10,
                R.drawable.pomme,
                new Nutriments(new double[]{75.4, 0.145, 0.435,
                        14.5, 1.45, 3.48})
        );
    }

    @Override
    public Nourriture clone() {
        return new Pomme();
    }

}

package com.example.myhumantom.aliments.fruits;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.R;
import com.example.myhumantom.aliments.Nourriture;

public class Banane extends Fruit {

    public Banane() {
        super("Banane",
                14,
                R.drawable.banane,
                new Nutriments(new double[]{108.6, 0.36, 1.32,
                        14.4, 1.2, 3.12})
        );
    }

    @Override
    public Nourriture clone() {
        return new Banane();
    }
}

package com.example.myhumantom.aliments.legumes;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.R;
import com.example.myhumantom.aliments.Nourriture;

public class Salade extends Legume {

    public Salade() {
        super("Salade",
                10,
                R.drawable.salade,
                new Nutriments(new double[]{43.35, 4.13, 3.9304,
                        2.12, 39.304, 3.468})
        );
    }

    @Override
    public Nourriture clone() {
        return new Salade();
    }

}

package com.example.myhumantom.aliments.viandes;

import com.example.myhumantom.aliments.Nutriments;
import com.example.myhumantom.R;
import com.example.myhumantom.aliments.Nourriture;

public class Poulet extends Viande {

    public Poulet() {
        super("Poulet",
                100,
                R.drawable.poulet,
                new Nutriments(new double[]{921, 42, 51,
                        0, 246, 0})
        );
    }

    @Override
    public Nourriture clone() {
        return new Poulet();
    }
}

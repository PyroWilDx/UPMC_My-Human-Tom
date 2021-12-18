package com.example.myhumantom.exceptions;

import com.example.myhumantom.aliments.Nourriture;

public class NonComposableException extends Exception {
    public NonComposableException(Nourriture nourriture1, Nourriture nourriture2) {
        super(nourriture1.getNom() + " et " + nourriture2.getNom() +
                " ne sont pas composables !");
    }
    public NonComposableException(Nourriture nourriture) {
        super(nourriture.getNom() + " n'est pas composable !");
    }
}

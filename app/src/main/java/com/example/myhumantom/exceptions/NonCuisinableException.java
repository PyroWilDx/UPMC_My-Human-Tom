package com.example.myhumantom.exceptions;

public class NonCuisinableException extends Exception {
    public NonCuisinableException() {
        super("Cet aliment n'est pas cuisinable !");
    }
}

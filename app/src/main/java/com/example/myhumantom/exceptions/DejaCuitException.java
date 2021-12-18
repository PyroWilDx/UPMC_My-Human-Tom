package com.example.myhumantom.exceptions;

public class DejaCuitException extends Exception {
    public DejaCuitException() {
        super("Cet aliment est déjà cuit !");
    }
}

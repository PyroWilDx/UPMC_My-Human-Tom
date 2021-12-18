package com.example.myhumantom.joueur;

import android.content.Context;

import java.io.IOException;
import java.io.Serializable;

public interface Sauvegardable extends Serializable {
    void sauvegarder(Context context) throws IOException;
}

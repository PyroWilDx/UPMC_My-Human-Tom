package com.example.myhumantom.aliments.interfaces;

import com.example.myhumantom.aliments.Plat;

public interface Composable {
    Plat composer(Composable composable);
}

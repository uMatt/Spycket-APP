package com.cqrit.spycket.models;

import androidx.annotation.NonNull;

public class CaptureData {
    private final String nom;

    // Constructor
    public CaptureData(String nom) {
        this.nom = nom;
    }

    // Getters
    @NonNull
    public String toString() {
        return this.nom;
    }
}
package com.cqrit.spycket.models;

import androidx.annotation.NonNull;

public class CaptureData {
    private final String nom;
    private final String id_execution;

    // Constructor
    public CaptureData(String nom, String id_execution) {
        this.nom = nom;
        this.id_execution = id_execution;
    }

    // Getters
    @NonNull
    public String toString() {
        return this.nom;
    }

    public String getId() {
        return id_execution;
    }
}
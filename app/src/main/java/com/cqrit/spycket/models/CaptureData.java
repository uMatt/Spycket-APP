package com.cqrit.spycket.models;

import androidx.annotation.NonNull;

public class CaptureData {
    private final int id_execution;
    private final String nom;

    // Constructor
    public CaptureData(int id_execution, String nom) {
        this.id_execution = id_execution;
        this.nom = nom;
    }

    // Getters


    @NonNull
    public String toString() {
        return this.nom;
    }

    public int getId_execution() {
        return id_execution;
    }
}
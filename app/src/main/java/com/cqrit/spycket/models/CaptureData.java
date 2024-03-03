package com.cqrit.spycket.models;

public class CaptureData {
    private final int numero_trame;
    private final int id;
    private final String ip;

    // Constructor
    public CaptureData(int numero_trame, int id, String ip) {
        //TODO  : CHANGE NAME OF VARIABLES
        this.numero_trame = numero_trame;
        this.id = id;
        this.ip = ip;
    }

    // Getters
    public int getUserId() {
        return numero_trame;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return ip;
    }

    //TODO : CHANGE NAME OF WHAT WE WANT
    public String toString() {
        return this.ip;
    }
}
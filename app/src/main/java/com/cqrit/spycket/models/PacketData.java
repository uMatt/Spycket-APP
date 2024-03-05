package com.cqrit.spycket.models;

public class PacketData {
    private final int userId;
    private final int id;
    private final String title;
    private final boolean completed;
    private final String numero_trame;

    // Constructor
    public PacketData(int userId, int id, String title, boolean completed, String numero_trame) {
        //TODO  : CHANGE NAME OF VARIABLES
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.numero_trame = numero_trame;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    //TODO : CHANGE NAME OF WHAT WE WANT
    public String toString() {
        return String.valueOf(this.id);
    }

    public String getTrame() {
        return numero_trame;
    }
}
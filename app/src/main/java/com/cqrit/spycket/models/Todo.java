package com.cqrit.spycket.models;

public class Todo {
    private final int userId;
    private final int id;
    private final String title;
    private final boolean completed;

    // Constructor
    public Todo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
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
}
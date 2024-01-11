package com.equitativa.model;

public enum Priority {

    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");


    private final String name;


    Priority(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

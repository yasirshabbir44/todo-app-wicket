package com.equitativa.model.enumerate;

public enum Status {

    COMPLETED("COMPLETED"),
    PENDING("PENDING");


    private final String name;


    Status(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

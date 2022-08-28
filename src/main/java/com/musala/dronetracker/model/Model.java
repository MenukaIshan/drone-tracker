package com.musala.dronetracker.model;

public enum Model {
    Lightweight("Lightweight"), Middleweight("Middleweight"),
    Cruiserweight("Cruiserweight"), Heavyweight("Heavyweight");

    private String modelValue;

    Model(String modelValue) {
        this.modelValue = modelValue;
    }

    public String getModelValue() {
        return modelValue;
    }
}
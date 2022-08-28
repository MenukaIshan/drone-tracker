package com.musala.dronetracker.model;

public enum State {
    IDLE("IDLE"), LOADING("LOADING"), LOADED("LOADED"), DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"), RETURNING("RETURNING");

    private String stateValue;

    State(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateValue() {
        return stateValue;
    }
}

package com.studia.io.model;

public enum  GameMode {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private String mode;

    GameMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}

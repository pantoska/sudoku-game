package com.studia.io.model;

public class Cell {
    private String state;
    private int row;
    private int column;

    public Cell(String state, int row, int column) {
        this.state = state;
        this.row = row;
        this.column = column;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

package com.studia.io.model;

public class BoardRepository {
    public static final int SIZE = 9;
    private int[][] board;

    public BoardRepository(int[][] board) {
        this.board = board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard(){
        return board;
    }


}

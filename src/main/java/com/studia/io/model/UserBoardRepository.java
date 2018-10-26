package com.studia.io.model;

public class UserBoardRepository {
    private int board[][];

    public UserBoardRepository(int[][] board) {
        this.board = board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard(){
        return board;
    }
}

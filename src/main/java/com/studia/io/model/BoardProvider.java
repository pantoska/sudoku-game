package com.studia.io.model;

import com.studia.io.controller.BoardGenerator;

public class BoardProvider {
    private final BoardGenerator boardGenerator = new BoardGenerator();

    public int[][] getBoard() {
        return boardGenerator.getBoard();
    }

    public int[][] getUserBoard() { return boardGenerator.getUserBoard();}

    public void modifyCells(int value, int row, int column){ boardGenerator.modifyCells(value,row,column);}
}

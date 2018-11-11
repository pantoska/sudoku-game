package com.studia.io.model;

import com.studia.io.controller.BoardGenerator;
import com.studia.io.error.InvalidDataInputEx;

public class BoardProvider {
    private final BoardGenerator boardGenerator = new BoardGenerator();

    public int[][] getBoard(String mode) { return boardGenerator.getBoard(mode); }

    public int[][] getDefaultBoard() {return  boardGenerator.getDefaultBoard();}

    public int[][] getUserBoard() { return boardGenerator.getUserBoard();}

    public void modifyCells(int value, int row, int column) throws InvalidDataInputEx { boardGenerator.modifyCells(value,row,column);}

    public void clearCell(int row, int column) { boardGenerator.clearCell(row, column);}

    public void clearUserBoard() { boardGenerator.resetUserBoard();}

    public boolean isEndOfGame() { return boardGenerator.isEndOfGame();}

    public boolean checkStatus() { return boardGenerator.checkStatus();}
}

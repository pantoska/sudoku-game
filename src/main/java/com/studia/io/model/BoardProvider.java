package com.studia.io.model;

import com.studia.io.controller.BoardGenerator;
import com.studia.io.error.InvalidDataInputEx;

import java.util.List;

public class BoardProvider {
    private final BoardGenerator boardGenerator = new BoardGenerator();

    public int[][] renderBoard(String mode) { return boardGenerator.renderBoard(mode); }

    public int[][] getBoard() {return  boardGenerator.getBoard();}

    public void modifyCells(int value, int row, int column) throws InvalidDataInputEx { boardGenerator.modifyCells(value,row,column);}

    public void clearCell(int row, int column) { boardGenerator.clearCell(row, column);}

    public boolean isEndOfGame() { return boardGenerator.isEndOfGame();}

    public boolean checkStatus() { return boardGenerator.checkStatus();}

    public List<Cell> getCellList() { return boardGenerator.getCellList();}
}

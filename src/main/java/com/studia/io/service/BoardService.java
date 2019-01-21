package com.studia.io.service;

import com.studia.io.controller.BoardGenerator;
import com.studia.io.error.InvalidDataInputEx;
import com.studia.io.model.BoardRepository;
import com.studia.io.model.Cell;

import java.util.List;

public class BoardService {
    private BoardRepository boardRepository;
    private final BoardGenerator boardGenerator = new BoardGenerator();

    public BoardService() {
        boardRepository = new BoardRepository(boardGenerator.getBoard());
    }

    public void renderBoard(String mode) { boardGenerator.renderBoard(mode); }

    public int[][] getBoard() {return boardRepository.getBoard();}

    public void modifyCells(int value, int row, int column) throws InvalidDataInputEx { boardGenerator.modifyCells(value,row,column);}

    public void clearCell(int row, int column) { boardGenerator.clearCell(row, column);}

    public boolean checkStatus() { return boardGenerator.checkStatus();}

    public List<Cell> getCellList() { return boardGenerator.getCellList();}
}

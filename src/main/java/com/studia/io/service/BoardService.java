package com.studia.io.service;

import com.studia.io.error.InvalidDataInputEx;
import com.studia.io.model.BoardProvider;
import com.studia.io.model.BoardRepository;
import com.studia.io.model.Cell;

import java.util.List;

public class BoardService {
    private BoardRepository boardRepository;
    private final BoardProvider boardProvider = new BoardProvider();

    public BoardService() {
        boardRepository = new BoardRepository(boardProvider.getBoard());
    }

    public void renderBoard(String mode) { boardRepository.setBoard(boardProvider.renderBoard(mode)); }

    public void userInput(int value, int row, int column) throws InvalidDataInputEx { boardProvider.modifyCells(value, row, column);}

    public int[][] getBoard() { return boardRepository.getBoard(); }

    public void clearCell(int row, int value) { boardProvider.clearCell(row, value);}

    public boolean endOfGame() { return boardProvider.isEndOfGame();}

    public boolean checkStatus() { return boardProvider.checkStatus();}

    public List<Cell> getCellList() { return boardProvider.getCellList();}
}

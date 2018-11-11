package com.studia.io.service;

import com.studia.io.error.InvalidDataInputEx;
import com.studia.io.model.BoardProvider;
import com.studia.io.model.BoardRepository;
import com.studia.io.model.UserBoardRepository;

public class BoardService {
    private BoardRepository boardRepository;
    private final UserBoardRepository userBoardRepository;
    private final BoardProvider boardProvider = new BoardProvider();

    public BoardService() {
        boardRepository = new BoardRepository(boardProvider.getDefaultBoard());
        userBoardRepository = new UserBoardRepository(boardProvider.getUserBoard());
    }

    public void generateBoard(String mode) { boardRepository.setBoard(boardProvider.getBoard(mode)); }

    public int[][] getUserBoard() { return userBoardRepository.getBoard();}

    public void userInput(int value, int row, int column) throws InvalidDataInputEx { boardProvider.modifyCells(value, row, column);}

    public int[][] getBoard() { return boardRepository.getBoard(); }

    public void clearCell(int row, int value) { boardProvider.clearCell(row, value);}

    public void clearUserBoard() { boardProvider.clearUserBoard();}

    public boolean endOfGame() { return boardProvider.isEndOfGame();}

    public boolean checkStatus() { return boardProvider.checkStatus();}
}

package com.studia.io.service;

import com.studia.io.model.BoardProvider;
import com.studia.io.model.BoardRepository;

public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardProvider boardProvider = new BoardProvider();

    public BoardService() {
        boardRepository = new BoardRepository(boardProvider.getBoard());
    }

    public void generateBoard() {
        boardRepository.setBoard(boardProvider.getBoard());
    }

    public int[][] getBoard() {
        return boardRepository.getBoard();
    }
}

package com.studia.io.controller;

import com.studia.io.model.BoardRepository;
import com.studia.io.model.GameMode;
import com.studia.io.validation.BoardValidation;

import java.util.Random;

public class BoardGenerator {
    private int clearCells;
    private int[][] board = new int[BoardRepository.SIZE][BoardRepository.SIZE];
    private int[][] userBoard = new int[BoardRepository.SIZE][BoardRepository.SIZE];
    private int[][] currentBoard = new int[BoardRepository.SIZE][BoardRepository.SIZE];
    private static final Random random = new Random();
    private final BoardValidation boardValidation= new BoardValidation();

    public int[][] getBoard(String mode){
        setMode(mode);
        setFullBoard(0,0);
        clearRandomCells();
        return board;
    }

    public int[][] getDefaultBoard(){
        return board;
    }

    public int[][] getUserBoard(){
        return userBoard;
    }

    private void setMode(String mode){
        if (mode.equals(GameMode.EASY.getMode()))
            clearCells = 45;
        else if(mode.equals(GameMode.MEDIUM.getMode()))
            clearCells = 65;
        else if(mode.equals(GameMode.HARD.getMode()))
            clearCells = 85;
    }

    private boolean setFullBoard(int row, int column)
    {
        int i=0;
        do {
            int nextRow, nextColumn;
            board[row][column] = random.nextInt(9) + 1;
            if ( boardValidation.checkColumn(row, column,board) && boardValidation.checkRow(row, column, board) && boardValidation.checkSquare(row, column, board)) {
                nextRow = row;
                nextColumn = column;
                nextColumn++;

                if(nextColumn > 8) {
                    nextColumn = 0;
                    nextRow++;
                }

                if (nextColumn == 0 && nextRow == 9)
                    return true;

                if(setFullBoard(nextRow, nextColumn))
                    return true;
            }
            i++;
        } while(i < 9);

        board[row][column] = 0;
        return false;
    }

    public void clearCell(int row, int column){
        userBoard[row][column] = 0;
        currentBoard[row][column] = 0;
    }

    private void clearRandomCells(){
        while(clearCells != 0){
            int clearX = random.nextInt(9);
            int clearY = random.nextInt(9);
            board[clearX][clearY] = 0;
            clearCells--;
        }
    }

    public void clearUserBoard(){
        for(int i=0; i <BoardRepository.SIZE; i++)
            for(int j =0; j<BoardRepository.SIZE;j++)
                userBoard[i][j] = 0;
    }

    public void modifyCells(int value, int row, int column){
        if(board[row][column] == 0)
            if(value > 0 && value <= 9 && boardValidation.checkInput(value,row,column,board, userBoard, currentBoard))
                userBoard[row][column] = value;
            else
                boardValidation.message("Error");
    }

}

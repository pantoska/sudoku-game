package com.studia.io.controller;

import com.studia.io.error.InvalidDataInputEx;
import com.studia.io.model.BoardRepository;
import com.studia.io.model.GameMode;
import com.studia.io.validation.BoardValidation;

import java.util.Random;

public class BoardGenerator {
    private int clearCells;
    private int[][] defaultBoard = new int[BoardRepository.SIZE][BoardRepository.SIZE]; //startboard with some generate numbers
    private int[][] userBoard = new int[BoardRepository.SIZE][BoardRepository.SIZE]; //defaultBoard with user inputs
    private int[][] currentBoard = new int[BoardRepository.SIZE][BoardRepository.SIZE]; //defaultBoard connected with defaultBoard and userBoard
    private static final Random random = new Random();
    private final BoardValidation boardValidation = new BoardValidation();

    public int[][] getStartBoard(String mode){
        resetMainBoard();
        resetUserBoard();
        setMode(mode);
        setDefaultBoard(0,0);
        clearRandomCells();
        return getDefaultBoard();
    }

    public int[][] getDefaultBoard(){
        return defaultBoard;
    }

    public int[][] getUserBoard(){
        return userBoard;
    }

    private void setMode(String mode){
        if (mode.equals(GameMode.EASY.getMode()))
            clearCells = 40;
        else if(mode.equals(GameMode.MEDIUM.getMode()))
            clearCells = 50;
        else if(mode.equals(GameMode.HARD.getMode()))
            clearCells = 60;
    }

    private boolean setDefaultBoard(int row, int column)
    {
        int i=0;
        do {
            int nextRow, nextColumn;
            defaultBoard[row][column] = random.nextInt(9) + 1;
            if ( boardValidation.checkColumn(row, column, defaultBoard) && boardValidation.checkRow(row, column, defaultBoard) && boardValidation.checkSquare(row, column, defaultBoard)) {
                nextRow = row;
                nextColumn = column;
                nextColumn++;

                if(nextColumn > 8) {
                    nextColumn = 0;
                    nextRow++;
                }

                if (nextColumn == 0 && nextRow == 9)
                    return true;

                if(setDefaultBoard(nextRow, nextColumn))
                    return true;
            }
            i++;
        } while(i < 9);

        defaultBoard[row][column] = 0;
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
            defaultBoard[clearX][clearY] = 0;
            clearCells--;
        }
    }

    public void resetUserBoard(){
        for(int i=0; i <BoardRepository.SIZE; i++)
            for(int j =0; j<BoardRepository.SIZE;j++)
                userBoard[i][j] = 0;
    }

    public void modifyCells(int value, int row, int column) throws InvalidDataInputEx {
        if(defaultBoard[row][column] == 0)
            if(value > 0 && value <= 9 && boardValidation.checkInput(value,row,column, defaultBoard, userBoard, currentBoard))
                userBoard[row][column] = value;
            else
               throw new InvalidDataInputEx("Invalid input value");
    }

    public boolean checkStatus(){
        for(int i=0; i <BoardRepository.SIZE; i++)
            for(int j =0; j<BoardRepository.SIZE;j++)
                if (currentBoard[i][j] == 0 && userBoard[i][j] == 0) {
                    return false;
                }
        return true;
    }

    public boolean isEndOfGame(){
        return checkStatus();
    }

    private void resetMainBoard(){
        for(int i=0; i <BoardRepository.SIZE; i++)
            for(int j =0; j<BoardRepository.SIZE;j++) {
                defaultBoard[i][j] = 0;
                currentBoard[i][j] = 0;
            }
    }

}

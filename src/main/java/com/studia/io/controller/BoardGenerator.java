package com.studia.io.controller;

import com.studia.io.model.BoardRepository;

import java.util.Random;

public class BoardGenerator {
    private int clearCells = 45;
    private int[][] board = new int[BoardRepository.SIZE][BoardRepository.SIZE];
    private int[][] userBoard = new int[BoardRepository.SIZE][BoardRepository.SIZE];
    private int[][] removedNumbersBoard = new int[BoardRepository.SIZE][BoardRepository.SIZE];
    private int[][] currentBoard = new int[BoardRepository.SIZE][BoardRepository.SIZE];

    public int[][] getBoard(){
        Random random = new Random(1000000000);
        setFullBoard(random,0,0);
        clearRandomCells(random);
        return board;
    }

    public BoardGenerator setMode(String mode){
        if (mode.equals("easy"))
            clearCells = 45;
        else if(mode.equals("medium"))
            clearCells = 65;
        else if(mode.equals("hard"))
            clearCells = 85;
        return this;
    }

    public int[][] getUserBoard(){
        return userBoard;
    }

    public int[][] getRemovedNumbersBoard(){
        return removedNumbersBoard;
    }

    private boolean checkRow(int row, int column, int[][] b) {
        int i=0;
        while ( i < 9 ) {
            if (i != column) {
                if (b[row][i] == b[row][column] )
                    return false;
            }
            i++;
        }
        return true;
    }

    private boolean checkColumn(int row, int column, int[][] b) {
        int i=0;
        while ( i < 9 ) {
            if (i != row) {
                if (b[i][column] == b[row][column])
                    return false;

            }
            i++;
        }
        return true;
    }

    private boolean checkSquare(int row, int column, int[][] b) {
        int height = row/3;
        int width = column/3;

        for (int i = height * 3; i < (height*3 + 3); i++) {
            for (int j = width * 3; j < (width*3 + 3); j++) {
                if (!(i == row && j == column)) {
                    if (b[ row ][ column ] == b[i][j])
                        return false;
                }
            }
        }
        return true;
    }

    private boolean setFullBoard(Random random, int row, int column)
    {
        int i=0;
        do {
            int nextRow, nextColumn;

            board[row][column] = random.nextInt(9) + 1;

            if ( checkColumn(row, column,board) && checkRow(row, column, board) && checkSquare(row, column, board)) {
                nextRow = row;
                nextColumn = column;
                nextColumn++;

                if(nextColumn > 8) {
                    nextColumn = 0;
                    nextRow++;
                }

                if (nextColumn == 0 && nextRow == 9)
                    return true;

                if(setFullBoard(random, nextRow, nextColumn))
                    return true;
            }
            i++;
        } while(i < 9);

        board[row][column] = 0;
        return false;
    }

    public void clearCell(int row, int column){
        userBoard[row][column] = 0;
    }

    private void clearRandomCells(Random random){
        while(clearCells != 0){
            int clearX = random.nextInt(8) + 1;
            int clearY = random.nextInt(8) + 1;

            removedNumbersBoard[clearX][clearY] = board[clearX][clearY];

            board[clearX][clearY] = 0;
            clearCells--;
        }
    }

    private boolean checkInput(int value, int row, int column){

        for(int i=0; i <BoardRepository.SIZE; i++){
            for(int j =0; j<BoardRepository.SIZE;j++){
                if(board[i][j] != 0){
                    currentBoard[i][j] = board[i][j];
                }
                else if(userBoard[i][j] != 0){
                    currentBoard[i][j] = board[i][j];
                }
            }
        }

        currentBoard[row][column] = value;

        if(checkColumn(row, column,currentBoard) && checkRow(row, column, currentBoard) && checkSquare(row, column, currentBoard)){
            return true;
        }
        return false;
    }

    public void modifyCells(int value, int row, int column){
        if(board[row][column] == 0)
            if(value > 0 && value <= 9 && checkInput(value,row,column))
                userBoard[row][column] = value;

    }
}

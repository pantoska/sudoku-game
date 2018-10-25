package com.studia.io.controller;

import com.studia.io.model.BoardRepository;

import java.util.Random;

public class BoardGenerator {
    private int clearCells = 45;
    private int[][] board = new int[BoardRepository.SIZE][BoardRepository.SIZE];
    private final Random random = new Random();

    public int[][] getBoard(){
        setFullBoard(0,0);
        clearRandomCells();
        return board;
    }

    private boolean checkRow(int row, int column) {
        int i=0;
        while ( i < 9 ) {
            if (i != column) {
                if (board[row][i] == board[row][column] )
                    return false;
            }
            i++;
        }
        return true;
    }

    private boolean checkColumn(int row, int column) {
        int i=0;
        while ( i < 9 ) {
            if (i != row) {
                if (board[i][column] == board[row][column])
                    return false;

            }
            i++;
        }
        return true;
    }

    private boolean checkSquare(int row, int column) {
        int height = row/3;
        int width = column/3;

        for (int i = height * 3; i < (height*3 + 3); i++) {
            for (int j = width * 3; j < (width*3 + 3); j++) {
                if (!(i == row && j == column)) {
                    if (board[ row ][ column ] == board[i][j])
                        return false;
                }
            }
        }
        return true;
    }

    private boolean setFullBoard(int row, int column)
    {
        int i=0;

        do {
            int nextRow, nextColumn;

            board[row][column] = random.nextInt(9) + 1;

            if ( checkColumn(row, column) && checkRow(row, column) && checkSquare(row, column)) {
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

    private void clearRandomCells(){

        while(clearCells != 0){
            int clearX = random.nextInt(8) + 1;
            int clearY = random.nextInt(8) + 1;

            board[clearX][clearY] = 0;
            clearCells--;
        }
    }

}

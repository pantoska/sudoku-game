package com.studia.io.validation;

public class BoardValidation {

    public boolean checkRow(int row, int column, int[][] b) {
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

    public boolean checkColumn(int row, int column, int[][] b) {
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

    public boolean checkSquare(int row, int column, int[][] b) {
        int height = row/3;
        int width = column/3;

        for (int i = height * 3; i < (height*3 + 3); i++)
            for (int j = width * 3; j < (width*3 + 3); j++)
                if (!(i == row && j == column))
                    if (b[ row ][ column ] == b[i][j])
                        return false;

        return true;
    }

    public boolean checkInput(int value, int row, int column, int[][] currentBoard){

        currentBoard[row][column] = value;

        if(checkColumn(row, column,currentBoard) && checkRow(row, column, currentBoard) && checkSquare(row, column, currentBoard))
            return true;

        return false;
    }

}

package com.studia.io.validation;

import com.studia.io.model.BoardRepository;
import javafx.scene.control.Alert;

public class BoardValidation {
    private static final Alert alert = new Alert(Alert.AlertType.ERROR);
    private static final Alert message = new Alert(Alert.AlertType.INFORMATION);

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

    public boolean checkInput(int value, int row, int column, int[][] board, int[][] userBoard, int[][] currentBoard){

        for(int i = 0; i <BoardRepository.SIZE; i++)
            for(int j =0; j<BoardRepository.SIZE;j++)
                if(board[i][j] != 0)
                    currentBoard[i][j] = board[i][j];
                else if(userBoard[i][j] != 0){
                    currentBoard[i][j] = userBoard[i][j];
        }

        currentBoard[row][column] = value;

        if(checkColumn(row, column,currentBoard) && checkRow(row, column, currentBoard) && checkSquare(row, column, currentBoard))
            return true;
        else
            currentBoard[row][column] = 0;

        return false;
    }

    public void message(String type){
        alert.setTitle("Error Dialog");
        alert.setHeaderText(type);
        alert.setContentText("You can not input this value here");
        alert.showAndWait();
    }

    public void messageEndGame(){
        message.setTitle("End of game");
        message.setContentText("You resolved the board! Congratulation");
        message.showAndWait();

    }

}

package com.studia.io.controller;

import com.studia.io.error.InvalidDataInputEx;
import com.studia.io.model.BoardRepository;
import com.studia.io.model.Cell;
import com.studia.io.model.GameMode;
import com.studia.io.validation.BoardValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardGenerator {
    private int clearCells;
    private int[][] defaultBoard = new int[BoardRepository.SIZE][BoardRepository.SIZE]; //startboard with some generate numbers
    private static final Random random = new Random();
    private final BoardValidation boardValidation = new BoardValidation();

    private Cell cell;
    private List<Cell> cellList;

    public int[][] renderBoard(String mode){
        cellList = new ArrayList<>();
        resetBoard();
        setMode(mode);
        setBoard(0,0);
        clearRandomCells();
        return getBoard();
    }

    public int[][] getBoard(){
        return defaultBoard;
    }

    private void setMode(String mode){
        if (mode.equals(GameMode.EASY.getMode()))
            clearCells = 40;
        else if(mode.equals(GameMode.MEDIUM.getMode()))
            clearCells = 50;
        else if(mode.equals(GameMode.HARD.getMode()))
            clearCells = 60;
    }

    private boolean setBoard(int row, int column)
    {
        int i=0;
        do {
            int nextRow, nextColumn;
            defaultBoard[row][column] = random.nextInt(9) + 1;
            if (boardValidation.checkColumn(row, column, defaultBoard) && boardValidation.checkRow(row, column, defaultBoard) && boardValidation.checkSquare(row, column, defaultBoard)) {

                if(row == 8 && column == 8){
                    cell = new Cell("default",row,column);
                    cellList.add(cell);
                }

                nextRow = row;
                nextColumn = column;
                nextColumn++;

                if(nextColumn > 8) {
                    nextColumn = 0;
                    nextRow++;
                }

                if (nextColumn == 0 && nextRow == 9)
                    return true;

                if(setBoard(nextRow, nextColumn)){
                    cell = new Cell("default",row,column);
                    cellList.add(cell);
                    return true;
                }
            }
            i++;
        } while(i < 9);

        defaultBoard[row][column] = 0;
        return false;
    }

    public void clearCell(int row, int column){
        for(Cell c: cellList){
            if(c.getState().equals("user") && c.getRow() == row && c.getColumn() == column) {
                defaultBoard[row][column] = 0;
            }
        }
    }

    private void clearRandomCells(){
        while(clearCells != 0){
            int clearX = random.nextInt(9);
            int clearY = random.nextInt(9);

            for(Cell c: cellList){
                if(c.getRow() == clearX && c.getColumn() == clearY && c.getState().equals("default")) {
                    c.setState("user");
                    defaultBoard[clearX][clearY] = 0;
                }
            }
            clearCells--;
        }
    }

    public void modifyCells(int value, int row, int column) throws InvalidDataInputEx {
        for(Cell c: cellList){
            if(c.getRow() == row && c.getColumn() == column && c.getState().equals("user")){
                if(defaultBoard[row][column] == 0) {

                    if (boardValidation.checkInput(value, row, column, defaultBoard)) {
                        defaultBoard[row][column] = value;
                    } else {
                        defaultBoard[row][column] = 0;
                        throw new InvalidDataInputEx("Invalid input value");
                    }

                } else if (defaultBoard[row][column] != 0){

                    int previous = defaultBoard[row][column];
                    if (boardValidation.checkInput(value, row, column, defaultBoard)) {
                        defaultBoard[row][column] = value;
                    } else {
                        defaultBoard[row][column] = previous;
                        throw new InvalidDataInputEx("Invalid input value");
                    }

                }
            }
        }
    }

    public boolean checkStatus(){
        for(int i=0; i <BoardRepository.SIZE; i++)
            for(int j =0; j<BoardRepository.SIZE;j++)
                if(defaultBoard[i][j] == 0)
                    return false;
        return true;
    }

    public boolean isEndOfGame(){
        return checkStatus();
    }

    private void resetBoard(){
        if(cellList.size() != 0)
            cellList.clear();

        for(int i=0; i <BoardRepository.SIZE; i++)
            for(int j =0; j<BoardRepository.SIZE;j++) {
                defaultBoard[i][j] = 0;
            }
    }

    public List<Cell> getCellList() {
        return cellList;
    }
}

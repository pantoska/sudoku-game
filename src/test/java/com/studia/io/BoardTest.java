package com.studia.io;

import com.studia.io.controller.BoardGenerator;
import com.studia.io.model.Cell;
import com.studia.io.model.GameMode;
import com.studia.io.validation.BoardValidation;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BoardTest {

    BoardValidation boardValidation = new BoardValidation();
    BoardGenerator boardGenerator = new BoardGenerator();

    int [][] board = {
            {3, 5, 0, 8, 9, 7, 2, 1, 6},
            {2, 6, 8, 4, 3, 1, 5, 9, 7},
            {7, 1, 9, 5, 6, 0, 4, 8, 3},
            {4, 0, 2, 6, 7, 8, 9, 0, 1},
            {6, 9, 7, 1, 5, 4, 8, 3, 2},
            {5, 8, 1, 9, 0, 3, 7, 6, 4},
            {8, 0, 6, 7, 1, 5, 3, 4, 9},
            {9, 4, 3, 2, 8, 6, 1, 1, 5},
            {1, 7, 5, 3, 4, 9, 6, 2, 8}
    };

    @Test
    public void testColumn(){
        Assert.assertEquals(false, boardValidation.checkColumn(7,7,board));
    }

    @Test
    public void testRow(){
        Assert.assertEquals(false,boardValidation.checkRow(7,7,board));
    }

    @Test
    public void testSquare(){
        Assert.assertEquals(false,boardValidation.checkSquare(7,7,board));
    }

    @Test
    public void testInput(){
        Assert.assertEquals(false,boardValidation.checkInput(8,0,0,board));
    }

    @Test
    public void testStatus(){
        boardGenerator.renderBoard(GameMode.EASY.getMode());
        Assert.assertEquals(false, boardGenerator.checkStatus());
    }

    @Test
    public void testCellList(){
        boardGenerator.renderBoard(GameMode.HARD.getMode());
        Assert.assertEquals(true, boardGenerator.getCellList().size() == 81);
    }

    @Test
    public void testCompareModes() {
        boardGenerator.renderBoard(GameMode.MEDIUM.getMode());
        int length1 = 0;
        List<Cell> list1 = boardGenerator.getCellList();
        for(Cell cell: list1) {
            if (cell.getState().equals("user"))
                length1++;
        }

        boardGenerator.renderBoard(GameMode.EASY.getMode());
        int length2 = 0;
        List<Cell> list2 = boardGenerator.getCellList();
        for(Cell cell: list2) {
            if (cell.getState().equals("user"))
                length2++;
        }
        Assert.assertEquals(true, length1 > length2);
    }

    @Test
    public void testCreateBoards() {
        boardGenerator.renderBoard(GameMode.HARD.getMode());
        int[][] firstBoard = boardGenerator.getBoard();
        boardGenerator.renderBoard(GameMode.HARD.getMode());
        int[][] secondBoard = boardGenerator.getBoard();
        Assert.assertEquals(false, firstBoard != secondBoard);
    }

    @Test
    public void testMode()
    {
        boardGenerator.renderBoard(GameMode.MEDIUM.getMode());
        int length = 0;
        List<Cell> list = boardGenerator.getCellList();
        for(Cell cell: list) {
            if (cell.getState().equals("user"))
                length++;
        }
        Assert.assertEquals(true, length <= 50);
    }
}

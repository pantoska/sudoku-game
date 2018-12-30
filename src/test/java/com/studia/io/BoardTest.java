package com.studia.io;

import com.studia.io.validation.BoardValidation;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    BoardValidation boardValidation = new BoardValidation();

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

//    @Test
//    public void testInput() throws Exception{
//        int userInput=2;
//        BoardGenerator boardGenerator = new BoardGenerator();
//        boardGenerator.modifyCells(userInput,3,1);
//    }
    @Test
    public void testInput(){
        Assert.assertEquals(false,boardValidation.checkInput(8,0,0,board));
    }


}

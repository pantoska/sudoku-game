package com.studia.io.view;

import com.studia.io.model.BoardRepository;
import com.studia.io.model.Cell;
import com.studia.io.service.BoardService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BoardView {

    private final int sizeOfBoard = 450;

    public void drawTable(GraphicsContext context, int selectedColumn, int selectedRow) {

        context.clearRect(0, 0, sizeOfBoard, sizeOfBoard);
        for (int i = 0; i < BoardRepository.SIZE; i++) {
            for (int j = 0; j < BoardRepository.SIZE; j++) {
                int positionX = i * 50 + 2;
                int positionY = j * 50 + 2;
                int width = 46;
                context.setFill(Color.WHITE);
                context.fillRoundRect(positionX, positionY, width, width, 10, 10);
            }
        }
        context.setStroke(Color.GRAY);
        context.setLineWidth(5);
        context.strokeRoundRect(selectedColumn * 50 + 2, selectedRow * 50 + 2, 46, 46, 10, 10);
    }

    public void drawInputs(GraphicsContext context, BoardService boardService) {
        int[][] initial = boardService.getBoard();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                int position_y = row * 50 + 30;
                int position_x = col * 50 + 20;


                for(Cell c: boardService.getCellList()){
                    if(c.getRow() == row && c.getColumn() == col){

                        if(c.getState().equals("default")){

                            context.setFill(Color.BLACK);
                            context.setFont(new Font(20));

                            if (initial[row][col] != 0)
                                context.fillText(initial[row][col] + "", position_x, position_y);
                        }

                        else if(c.getState().equals("user")){

                            context.setFill(Color.RED);
                            context.setFont(new Font(20));

                            if (initial[row][col] != 0)
                                context.fillText(initial[row][col] + "", position_x, position_y);
                        }
                    }
                }
            }
        }
    }
}

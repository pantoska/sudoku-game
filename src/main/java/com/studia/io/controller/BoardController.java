package com.studia.io.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;


public class BoardController implements Initializable {

    @FXML Canvas canvas;

    private final int numberOfCells = 9;
    private final int x = 235;
    private final int y = 30;
    private final int sizeOfCanvas = 450;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawBoard(context);

    }

    public void drawBoard(GraphicsContext context) {
        context.clearRect(x, y, sizeOfCanvas, sizeOfCanvas);
        for(int i =0; i<numberOfCells; i++){
            for(int j=0; j<numberOfCells;j++){
                int position_x = i * 50 +2;
                int position_y = j * 50 + 2;
                int width = 46;
                context.setFill(Color.GRAY);
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);
            }
        }

    }
}

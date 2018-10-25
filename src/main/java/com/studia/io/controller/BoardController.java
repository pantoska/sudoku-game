package com.studia.io.controller;

import com.studia.io.model.BoardRepository;
import com.studia.io.service.BoardService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;


public class BoardController implements Initializable {

    @FXML
    Canvas canvas;

    private final int x = 250;
    private final int y = 20;
    private final int sizeOfCanvas = 450;
    private int selectedRow;
    private int selectedColumn;
    private final BoardService boardService = new BoardService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawBoard(context);
        selectedRow = 0;
        selectedColumn = 0;
    }

    private void drawBoard(GraphicsContext context) {

        context.clearRect(0, 0, sizeOfCanvas, sizeOfCanvas);
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

        drawFilledBoard(context);
    }

    public void mouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int mouseX = (int) event.getX();
                int mouseY = (int) event.getY();

                selectedRow = (int) (mouseY / 50);
                selectedColumn = (int) (mouseX / 50);

                drawBoard(canvas.getGraphicsContext2D());
            }
        });

    }

    private void drawFilledBoard(GraphicsContext context) {
        int[][] initial = boardService.getBoard();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                int position_y = row * 50 + 30;
                int position_x = col * 50 + 20;

                context.setFill(Color.BLACK);
                context.setFont(new Font(20));

                if (initial[row][col] != 0) {
                    context.fillText(initial[row][col] + "", position_x, position_y);

                }
            }
        }
    }
}

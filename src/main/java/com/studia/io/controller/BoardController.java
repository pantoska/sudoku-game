package com.studia.io.controller;

import com.studia.io.model.BoardRepository;
import com.studia.io.service.BoardService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;


public class BoardController implements Initializable {

    @FXML Canvas canvas;
    @FXML Button button1;
    @FXML Button button2;
    @FXML Button button3;
    @FXML Button button4;
    @FXML Button button5;
    @FXML Button button6;
    @FXML Button button7;
    @FXML Button button8;
    @FXML Button button9;
    @FXML Button buttonClear;
    @FXML MenuButton menuButton;
    @FXML MenuItem easyMode;
    @FXML MenuItem mediumMode;
    @FXML MenuItem hardMode;


    private final int x = 250;
    private final int y = 20;
    private final int sizeOfCanvas = 450;
    private int selectedRow;
    private int selectedColumn;
    private final BoardService boardService = new BoardService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawGame(context);
        selectedRow = 0;
        selectedColumn = 0;
        button1.setOnMouseClicked(this::buttonOnePressed);
        button2.setOnMouseClicked(this::buttonTwoPressed);
        button3.setOnMouseClicked(this::buttonThreePressed);
        button4.setOnMouseClicked(this::buttonFourPressed);
        button5.setOnMouseClicked(this::buttonFivePressed);
        button6.setOnMouseClicked(this::buttonSixPressed);
        button7.setOnMouseClicked(this::buttonSevenPressed);
        button8.setOnMouseClicked(this::buttonEightPressed);
        button9.setOnMouseClicked(this::buttonNinePressed);
        buttonClear.setOnMouseClicked(this::buttonClearPressed);
        easyMode.setOnAction(this::setModeEasy);
        mediumMode.setOnAction(this::setModeMedium);
        hardMode.setOnAction(this::setModeHard);

    }

    private void drawGame(GraphicsContext context) {

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
        drawFilledUserBoard(context);
    }

    public void mouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int mouseX = (int) event.getX();
                int mouseY = (int) event.getY();

                selectedRow = (int) (mouseY / 50);
                selectedColumn = (int) (mouseX / 50);

                drawGame(canvas.getGraphicsContext2D());
            }
        });

    }

    private void drawFilledBoard(GraphicsContext context) {
        int[][] initial = boardService.getBoard();
        //int[][] initial = boardGenerator.getBoard();

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

    private void drawFilledUserBoard(GraphicsContext context) {
        int[][] initial = boardService.getUserBoard();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                int position_y = row * 50 + 30;
                int position_x = col * 50 + 20;

                context.setFill(Color.RED);
                context.setFont(new Font(20));

                if (initial[row][col] != 0) {
                    context.fillText(initial[row][col] + "", position_x, position_y);

                }
            }
        }
    }

    public void buttonOnePressed(MouseEvent event){
        boardService.userInput(1, selectedRow, selectedColumn);
        drawGame(canvas.getGraphicsContext2D());
    }

    public void buttonTwoPressed(MouseEvent event){
        boardService.userInput(2, selectedRow, selectedColumn);
        drawGame(canvas.getGraphicsContext2D());
    }

    public void buttonThreePressed(MouseEvent event){
        boardService.userInput(3, selectedRow, selectedColumn);
        drawGame(canvas.getGraphicsContext2D());
    }

    public void buttonFourPressed(MouseEvent event){
        boardService.userInput(4, selectedRow, selectedColumn);
        drawGame(canvas.getGraphicsContext2D());
    }

    public void buttonFivePressed(MouseEvent event){
        boardService.userInput(5, selectedRow, selectedColumn);
        drawGame(canvas.getGraphicsContext2D());
    }

    public void buttonSixPressed(MouseEvent event){
        boardService.userInput(6, selectedRow, selectedColumn);
        drawGame(canvas.getGraphicsContext2D());
    }

    public void buttonSevenPressed(MouseEvent event){
        boardService.userInput(7, selectedRow, selectedColumn);
        drawGame(canvas.getGraphicsContext2D());
    }

    public void buttonEightPressed(MouseEvent event){
        boardService.userInput(8, selectedRow, selectedColumn);
        drawGame(canvas.getGraphicsContext2D());
    }

    public void buttonNinePressed(MouseEvent event){
        boardService.userInput(9, selectedRow, selectedColumn);
        drawGame(canvas.getGraphicsContext2D());
    }

    public void buttonClearPressed(MouseEvent event){
        boardService.clearCell(selectedRow,selectedColumn);
        drawGame(canvas.getGraphicsContext2D());
    }

    public void setModeEasy(ActionEvent event){
        boardService.generateBoardWithMode("easy");
        drawGame(canvas.getGraphicsContext2D());
    }

    public void setModeMedium(ActionEvent event){
        boardService.generateBoardWithMode("medium");
        drawGame(canvas.getGraphicsContext2D());
    }

    public void setModeHard(ActionEvent event){
        boardService.generateBoardWithMode("hard");
        drawGame(canvas.getGraphicsContext2D());
    }
}

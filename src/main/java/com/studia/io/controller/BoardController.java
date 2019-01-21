package com.studia.io.controller;

import com.studia.io.error.InvalidDataInputEx;
import com.studia.io.model.GameMode;
import com.studia.io.service.BoardService;
import com.studia.io.view.BoardView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

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

    private int selectedRow;
    private int selectedColumn;
    private final BoardService boardService = new BoardService();
    private final BoardView boardView = new BoardView();
    private boolean start = false;
    private boolean state = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        selectedRow = 0;
        selectedColumn = 0;
        createBoard(context);
        button1.setOnMouseClicked(this::buttonOneHandled);
        button2.setOnMouseClicked(this::buttonTwoHandled);
        button3.setOnMouseClicked(this::buttonThreeHandled);
        button4.setOnMouseClicked(this::buttonFourHandled);
        button5.setOnMouseClicked(this::buttonFiveHandled);
        button6.setOnMouseClicked(this::buttonSixHandled);
        button7.setOnMouseClicked(this::buttonSevenHandled);
        button8.setOnMouseClicked(this::buttonEightHandled);
        button9.setOnMouseClicked(this::buttonNineHandled);
        buttonClear.setOnMouseClicked(this::buttonClearHandled);
        easyMode.setOnAction(this::setModeEasy);
        mediumMode.setOnAction(this::setModeMedium);
        hardMode.setOnAction(this::setModeHard);
    }

    private void createBoard(GraphicsContext context){
        boardView.drawTable(context,selectedColumn,selectedRow);
        if(start){
            boardView.drawInputs(context, boardService);
            if(boardService.checkStatus()){
                message("null");
            }
        }
    }

    public void mouseClicked() {
        canvas.setOnMouseClicked(event -> {
            int mouseX = (int) event.getX();
            int mouseY = (int) event.getY();

            selectedRow = (int) (mouseY / 50);
            selectedColumn = (int) (mouseX / 50);

            createBoard(canvas.getGraphicsContext2D());
        });

    }

    public void buttonOneHandled(MouseEvent event){
        handleInput(1);
    }

    public void buttonTwoHandled(MouseEvent event){
        handleInput(2);
    }

    public void buttonThreeHandled(MouseEvent event){
        handleInput(3);
    }

    public void buttonFourHandled(MouseEvent event){
        handleInput(4);
    }

    public void buttonFiveHandled(MouseEvent event){
        handleInput(5);
    }

    public void buttonSixHandled(MouseEvent event){
        handleInput(6);
    }

    public void buttonSevenHandled(MouseEvent event){
        handleInput(7);
    }

    public void buttonEightHandled(MouseEvent event){
        handleInput(8);
    }

    public void buttonNineHandled(MouseEvent event){
        handleInput(9);
    }

    private void buttonClearHandled(MouseEvent event){
        if(start) {
            boardService.clearCell(selectedRow, selectedColumn);
            createBoard(canvas.getGraphicsContext2D());
        }
    }

    private void handleInput(int value) {
        try {
            boardService.modifyCells(value, selectedRow, selectedColumn);
        } catch (InvalidDataInputEx e) {
            message(e.getMessage());
        }
        createBoard(canvas.getGraphicsContext2D());
    }

    public void setModeEasy(ActionEvent event){
        setMode(GameMode.EASY.getMode());
    }

    public void setModeMedium(ActionEvent event){
        setMode(GameMode.MEDIUM.getMode());
    }

    public void setModeHard(ActionEvent event){
        setMode(GameMode.HARD.getMode());
    }

    private void setMode(String mode){
        start = true;
        boardService.renderBoard(mode);
        createBoard(canvas.getGraphicsContext2D());
    }

    private void message(String type){
        Alert message;
        if(type.equals("null")){
            message = new Alert(Alert.AlertType.INFORMATION);
            message.setTitle("End of game");
            message.setContentText("You resolved the board! Congratulation");
        }
        else{
            message = new Alert(Alert.AlertType.ERROR);
            message.setTitle("Error Dialog");
            message.setHeaderText(type);
            message.setContentText("You can not input this value here");
        }
        message.showAndWait();
    }
}

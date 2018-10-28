package com.studia.io;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SudokuApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getClassLoader().getResource("sample.fxml");
        Parent root = FXMLLoader.load(url);
        stage.setResizable(false);
        stage.setTitle("Sudoku");
        stage.setScene(new Scene(root, 710, 500));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

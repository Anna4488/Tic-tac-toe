package com.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override

    public void start(Stage window) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.start(window);
    }

    public static void main(String[] args) {
        launch(App.class);
    }
}
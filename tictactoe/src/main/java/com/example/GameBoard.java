package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameBoard extends Application {
    private GameController gameController;

    private final Button[][] buttons = new Button[3][3];
    private final Label statusText = new Label();
    private final Label leaderboardText = new Label();

    private GameService gameService;

    @Override
    public void start(Stage window) {
        gameService = new GameLogic();
        Player player1 = new HumanPlayer("Player 1", "X");
        Player player2 = new RobotPlayer("Player 2", "O");

        gameController = new GameController(gameService, player1, player2, buttons, statusText, leaderboardText);

        BorderPane layout = new BorderPane();

        layout.setBottom(createRestartButton());
        layout.setTop(statusText);
        layout.setCenter(createGrid());
        layout.setRight(leaderboardText);

        Scene scene = new Scene(layout, 500, 350);
        window.setTitle("Tic-Tac-Toe");
        window.setScene(scene);
        window.show();
    }

    // -------------------------------------------------------------------------------------------------

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = createButton(row, col);
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }
        return grid;
    }

    private Button createButton(int row, int col) {
        Button button = new Button("Empty");
        button.setMinSize(100, 100);
        button.setFocusTraversable(false);
        button.setOnAction(e -> gameController.handleButtonClick(row, col));
        return button;
    }

    private Button createRestartButton() {
        Button restartButton = new Button("Restart Game");
        restartButton.setOnAction(e -> gameController.restartGame());
        return restartButton;
    }

}
package com.example;

import java.util.Random;

import javafx.scene.control.Button;

public class RobotPlayer extends Player {

    public RobotPlayer(String name, String symbol) {
        super(name, symbol);
    }

    @Override
    public void makeMove(GameService gameService, Button[][] buttons) {
        Random random = new Random();
        int row = 0;
        int col = 0;

        while (true) {
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (gameService.markCell(row, col, getSymbol())) {
                buttons[row][col].setText(getSymbol());
                buttons[row][col].setDisable(true);
                break;
            }

        }

        buttons[row][col].setText(getSymbol());
        buttons[row][col].setDisable(true);

    }
}
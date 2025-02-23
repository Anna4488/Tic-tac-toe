package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {
    private final GameService gameService;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Button[][] buttons;
    private final Label statusText;
    private final Label leaderboardText;

    private final List<Player> leaderboard;

    public GameController(GameService gameService, Player player1, Player player2, Button[][] buttons, Label statusText,
            Label leaderboardText) {
        this.gameService = gameService;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.buttons = buttons;
        this.statusText = statusText;
        this.leaderboardText = leaderboardText;

        leaderboard = new ArrayList<>();
        leaderboard.add(player1);
        leaderboard.add(player2);

        updateStatusText();

    }

    public void handleButtonClick(int row, int col) {
        try {
            if (currentPlayer instanceof HumanPlayer) {
                if (gameService.markCell(row, col, currentPlayer.getSymbol())) {
                    buttons[row][col].setText(currentPlayer.getSymbol());
                    buttons[row][col].setDisable(true);
                    checkGameState();
                }
            }
        } catch (Exception e) {
            statusText.setText("Error handling button click: " + e.getMessage());
        }
    }

    public void checkGameState() {
        try {
            if (gameService.checkForWinner()) {
                statusText.setText(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") wins!");
                currentPlayer.setScore(currentPlayer.getScore() + 1);
                updateLeaderboard();
                disableAllButtons();
            } else if (gameService.isBoardFull()) {
                statusText.setText("It's a draw!");
            } else {
                switchPlayer();
            }
        } catch (Exception e) {
            statusText.setText("Error handling button click: " + e.getMessage());
        }

    }

    public void switchPlayer() {
        try {
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
            updateStatusText();
            if (currentPlayer instanceof RobotPlayer) {
                currentPlayer.makeMove(gameService, buttons);
                checkGameState();
            }
        } catch (Exception e) {
            statusText.setText("Error handling button click: " + e.getMessage());
        }
    }

    public void restartGame() {
        try {
            gameService.resetBoard();
            currentPlayer = player1;
            statusText.setText("Turn: " + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ")");

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("Empty");
                    buttons[i][j].setDisable(false);
                }
            }
            updateStatusText();
        } catch (Exception e) {
            statusText.setText("Error handling button click: " + e.getMessage());
        }
    }

    public void disableAllButtons() {
        try {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setDisable(true);
                }
            }
        } catch (Exception e) {
            statusText.setText("Error handling button click: " + e.getMessage());
        }
    }

    private void updateStatusText() {
        statusText.setText("Turn: " + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ")");
    }

    private void updateLeaderboard() {
        try {
            List<Player> sortedList = leaderboard.stream()
                    .sorted()
                    .collect(Collectors.toList());
            leaderboardText.setText(buildLeaderboardString(sortedList));
        } catch (Exception e) {
            statusText.setText("Error handling button click: " + e.getMessage());
        }
    }

    private String buildLeaderboardString(List<Player> sortedList) {
        return sortedList.stream()
                .map(player -> player.getName() + " (" + player.getSymbol() + ") - Score: " + player.getScore())
                .collect(Collectors.joining("\n", "Leaderboard:\n", ""));
    }
}

package com.example;

public interface GameService {
    boolean markCell(int row, int col, String symbol);

    boolean checkForWinner();

    boolean isBoardFull();

    void resetBoard();
}
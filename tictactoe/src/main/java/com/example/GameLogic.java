package com.example;

import java.util.Arrays;

public class GameLogic implements GameService {
    private final String[][] board;
    private int moves;

    public GameLogic() {
        board = new String[3][3];
        resetBoard();
    }

    public String[][] getBoard() {
        return board;
    }

    @Override
    public void resetBoard() {
        try {
            moves = 0;
            Arrays.stream(board)
                    .forEach(row -> Arrays.fill(row, "Empty"));
        } catch (Exception e) {
            System.err.println("Error handling button click: " + e.getMessage());
        }
    }

    @Override
    public boolean markCell(int row, int col, String symbol) {
        try {
            if (board[row][col].equals("Empty")) {
                board[row][col] = symbol;
                moves++;
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error marking cell: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isBoardFull() {
        return moves == 9;
    }

    @Override
    public boolean checkForWinner() {
        return checkVerticalColumns() || checkHorizontalRows() || checkDiagonals();
    }

    public boolean checkVerticalColumns() {
        try {
            if (!(board[0][0].equals("Empty")) && board[0][0].equals(board[1][0])
                    && board[0][0].equals(board[2][0])) {
                return true;
            } else if (!(board[0][1].equals("Empty")) && board[0][1].equals(board[1][1])
                    && board[0][1].equals(board[2][1])) {
                return true;
            } else if (!(board[0][2].equals("Empty")) && board[0][2].equals(board[1][2])
                    && board[0][2].equals(board[2][2])) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error marking cell: " + e.getMessage());
        }
        return false;

    }

    public boolean checkHorizontalRows() {
        try {
            if (!(board[0][0].equals("Empty")) && board[0][0].equals(board[0][1])
                    && board[0][0].equals(board[0][2])) {
                return true;
            } else if (!(board[1][0].equals("Empty")) && board[1][0].equals(board[1][1])
                    && board[1][0].equals(board[1][2])) {
                return true;
            } else if (!(board[2][0].equals("Empty")) && board[2][0].equals(board[2][1])
                    && board[2][0].equals(board[2][2])) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error marking cell: " + e.getMessage());
        }
        return false;
    }

    public boolean checkDiagonals() {
        try {
            if (!(board[0][0].equals("Empty")) && board[0][0].equals(board[1][1])
                    && board[0][0].equals(board[2][2])) {
                return true;
            } else if (!(board[0][2].equals("Empty")) && board[0][2].equals(board[1][1])
                    && board[0][2].equals(board[2][0])) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error marking cell: " + e.getMessage());
        }
        return false;
    }

}
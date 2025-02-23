package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GameLogicTest {

    private GameLogic gameLogic;

    @Before
    public void setUp() {
        gameLogic = new GameLogic();
        gameLogic.resetBoard();
    }

    @Test
    public void testInitializeBoard() {
        String[][] board = gameLogic.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals("Board should be initialized with empty spaces", "Empty", board[i][j]);
            }
        }
    }

    @Test
    public void testMakeMove() {
        assertTrue("Move should be successful", gameLogic.markCell(0, 0, "X"));
        assertEquals("Board should have 'X' at position (0,0)", "X", gameLogic.getBoard()[0][0]);
    }

    @Test
    public void testMakeMoveInvalid() {
        gameLogic.markCell(0, 0, "X");
        assertFalse("Move should be unsuccessful as the cell is already occupied", gameLogic.markCell(0, 0, "O"));
    }

    @Test
    public void testCheckWin() {
        gameLogic.markCell(0, 0, "X");
        gameLogic.markCell(0, 1, "X");
        gameLogic.markCell(0, 2, "X");
        assertTrue("Player 'X' should win", gameLogic.checkForWinner());
    }

    @Test
    public void testIsBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameLogic.markCell(i, j, "X");
            }
        }
        assertTrue("Board should be full", gameLogic.isBoardFull());
    }
}
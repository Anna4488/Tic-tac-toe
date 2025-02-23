package com.example;

import javafx.scene.control.Button;

public abstract class Player implements Comparable<Player> {
    private String name;
    private String symbol;
    private int score;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public abstract void makeMove(GameService gameService, Button[][] buttons);

    @Override
    public int compareTo(Player other) {
        if (this.score != other.score) {
            return Integer.compare(other.score, this.score);
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ") - Score: " + score;
    }

}
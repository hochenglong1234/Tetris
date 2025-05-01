package com.example;

import java.util.Scanner;

interface Game {
    void start(Scanner scanner) throws InterruptedException;

    int getScore();

    void gainScore();

    int getLevel();

    void gainLevel();
}

public class Game2 implements Game {
    Tetris_Board board;
    Leaderboard leaderboard;
    InputHandler inputhandler;
    ShapeFactory factory;
    int score;
    int level;
    boolean gameOver;

    public Game2(Tetris_Board board, Leaderboard leaderboard, InputHandler inputhandler) {
        this.board = board;
        this.leaderboard = leaderboard;
        this.factory = board.factory;
        this.score = 0;
        this.level = 0;
        this.gameOver = false;
        this.inputhandler = inputhandler;
    }

    @Override
    public void start(Scanner scanner) throws InterruptedException {
        while (!gameOver) {
            leaderboard.printLeaderboard();
            Shape shape = factory.createRandomShape();
            if (!board.dropRandomShape(shape)) {
                gameOver = true;
            }
        }
        inputhandler.gameEnd = true;
        inputhandler.join();
        System.out.println("Game Over! Enter your name: ");
        String name = scanner.nextLine();
        leaderboard.addEntry(name, score);
        leaderboard.printLeaderboard();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void gainScore() {
        score++;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void gainLevel() {
        level++;
    }
}
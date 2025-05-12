package com.example;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public final class Game {
    public Boards board;
    public Leaderboard leaderboard;
    public ShapeFactory factory;
    public int score;
    public int level;
    public boolean gameOver;

    public Game(Boards board, ShapeFactory factory, Leaderboard leaderboard, int score, int level, boolean gameOver) {
        this.board = board;
        this.factory = factory;
        this.leaderboard = leaderboard;
        this.score = score;
        this.level = level;
        this.gameOver = gameOver;
    }

    public void start(Scanner scanner) throws InterruptedException {
        Random random = new Random();
        int currentScore = score;
        List<LeaderboardEntry> entries = List.of();

        while (true) {
            Shape shape = factory.createRandomShape(random); // Different Factory
            boolean success = board.dropRandomShape(shape);

            if (!success) {
                break; // Game Over
            }

            currentScore += 10; // increase score per shape placed
        }

        System.out.println("Game Over! Enter your name:");
        String name = scanner.nextLine();
        entries = Leaderboard.addEntry(entries, name, currentScore);

        System.out.println("Final Score: " + currentScore);
        System.out.println("Leaderboard:");
        for (String entry : Leaderboard.getLeaderboardOutput(entries)) {
            System.out.println(entry);
        }
    }

}

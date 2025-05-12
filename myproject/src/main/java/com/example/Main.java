package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        InputHandler inputHandler = new InputHandler();
        ShapeFactory shapeFactory = new Shapes();
        Leaderboard leaderboard = new Leaderboard();
        Boards board = new Tetris_Board(12, 15, inputHandler, shapeFactory);
        Game game = new Game(board, shapeFactory, leaderboard, 0, 0, false);

        game.start(scanner);

        scanner.close();
    }
}

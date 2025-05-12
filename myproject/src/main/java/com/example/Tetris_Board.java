package com.example;

import java.util.Optional;

interface Boards {
    char[][] initializeBoard();

    boolean dropRandomShape(Shape shape) throws InterruptedException;

    boolean canMove(Shape shape, int x, int y);

    char[][] placeShape(Shape shape, int x, int y);

    int clearFullLines();

    String printBoard(Shape shape, int x, int y);
}

public class Tetris_Board implements Boards {
    public final int WIDTH;
    public final int HEIGHT;
    public char[][] board;
    public final InputHandler inputHandler;
    public final ShapeFactory factory;
    public int score = 0;

    public Tetris_Board(int WIDTH, int HEIGHT, InputHandler inputHandler,
            ShapeFactory factory) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.inputHandler = inputHandler;
        this.factory = factory;
        this.board = initializeBoard();
    }

    @Override
    public char[][] initializeBoard() {
        char[][] newBoard = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                newBoard[i][j] = (i == 0 || i == HEIGHT - 1 || j == 0 || j == WIDTH - 1) ? '#' : ' ';
            }
        }
        return newBoard;
    }

    @Override
    public boolean dropRandomShape(Shape shape) throws InterruptedException {
        int x = WIDTH / 2 - 1;
        int y = 1;

        if (!canMove(shape, x, y)) {
            return false;
        }

        while (true) {
            Thread.sleep(500);
            Optional<Integer> inputOpt = inputHandler.getUserInput(System.in);
            if (inputOpt.isPresent()) {
                int input = inputOpt.get();
                if (input == 4 && canMove(shape, x - 1, y)) {
                    x--;
                } else if (input == 6 && canMove(shape, x + 1, y)) {
                    x++;
                } else if (input == 5 && canMove(shape, x, y + 1)) {
                    y++;
                } else if (input == 8) {
                    Shape rotated = shape.rotateClockwise();
                    if (canMove(rotated, x, y)) {
                        shape = rotated;
                    }
                }
            }

            if (!canMove(shape, x, y + 1)) {
                board = placeShape(shape, x, y);
                break;
            }

            y++;
            System.out.print(printBoard(shape, x, y));
        }
        return true;
    }

    @Override
    public boolean canMove(Shape shape, int x, int y) {
        for (int i = 0; i < shape.getShape().length; i++) {
            for (int j = 0; j < shape.getShape()[i].length; j++) {
                if (shape.getShape()[i][j] != ' ') {
                    if (y + i < 0 || y + i >= HEIGHT || x + j < 0 || x + j >= WIDTH ||
                            board[y + i][x + j] != ' ') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public char[][] placeShape(Shape shape, int x, int y) {
        for (int i = 0; i < shape.getShape().length; i++) {
            for (int j = 0; j < shape.getShape()[i].length; j++) {
                if (shape.getShape()[i][j] != ' ') {
                    board[y + i][x + j] = shape.getShape()[i][j];
                }
            }
        }
        score += clearFullLines();
        return board;
    }

    @Override
    public int clearFullLines() {
        int cleared = 0;
        for (int i = HEIGHT - 2; i > 0; i--) {
            boolean fullLine = true;
            for (int j = 1; j < WIDTH - 1; j++) {
                if (board[i][j] == ' ') {
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {
                for (int k = i; k > 1; k--) {
                    System.arraycopy(board[k - 1], 1, board[k], 1, WIDTH - 2);
                }
                for (int j = 1; j < WIDTH - 1; j++) {
                    board[1][j] = ' ';
                }
                cleared++;
            }
        }
        return cleared;
    }

    @Override
    public String printBoard(Shape shape, int x, int y) {
        StringBuilder output = new StringBuilder();
        output.append("\033[H\033[2J"); // Clear screen
        output.append("\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean shapePrinted = false;
                if (shape != null && i >= y && i < y + shape.getShape().length &&
                        j >= x && j < x + shape.getShape()[0].length &&
                        shape.getShape()[i - y][j - x] != ' ') {
                    output.append(shape.getShape()[i - y][j - x]);
                    shapePrinted = true;
                }
                if (!shapePrinted)
                    output.append(board[i][j]);
            }
            output.append("\n");
        }
        output.append("Score: ").append(score).append("\n");
        return output.toString();
    }
}

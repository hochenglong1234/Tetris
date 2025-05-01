package com.example;

import java.io.IOException;

public class InputHandler2 extends Thread {
    volatile int lastKeyPressed = -1;
    volatile boolean gameEnd = false;

    public InputHandler2() {
        this.start();
    }

    public void run() {
        try {
            while (!gameEnd) {
                int key = System.in.read();
                if (key == '4' || key == '6' || key == '5' || key == '8') {
                    lastKeyPressed = key - '0';
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getUserInput() {
        int key = lastKeyPressed;
        lastKeyPressed = -1;
        return key;
    }
}

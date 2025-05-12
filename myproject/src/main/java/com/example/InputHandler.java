package com.example;

import java.io.InputStream;
import java.io.IOException;
import java.util.Optional;

public final class InputHandler {

    public Optional<Integer> getUserInput(InputStream in) {
        try {
            if (in.available() > 0) {
                int key = in.read();
                if (key == '4' || key == '5' || key == '6' || key == '8') {
                    return Optional.of(key - '0');
                }
            }
        } catch (IOException e) {
            // error
        }
        return Optional.empty();
    }
}

package com.example;

public final class Shape {
    char[][] shape;
    ShapeFactory factory;

    public Shape(char[][] shape, ShapeFactory factory) {
        this.shape = shape;
        this.factory = factory;
    }

    public char[][] getShape() {
        // Return a deep copy to preserve immutability
        char[][] copy = new char[shape.length][];
        for (int i = 0; i < shape.length; i++) {
            copy[i] = shape[i].clone();
        }
        return copy;
    }

    public Shape rotateClockwise() {
        int rows = shape.length;
        int cols = shape[0].length;
        char[][] rotated = new char[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = shape[i][j];
            }
        }

        return factory.createShape(rotated);
    }
}

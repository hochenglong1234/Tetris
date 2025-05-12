package com.example;

import java.util.Random;

interface ShapeFactory {
    Shape createRandomShape(Random random);

    Shape createShape(char[][] array);
}

public class Shapes implements ShapeFactory {
    public final char[][][] SHAPES = {
            { { 'O', 'O' }, { 'O', 'O' } },
            { { 'I' }, { 'I' }, { 'I' }, { 'I' } },
            { { 'T', 'T', 'T' }, { ' ', 'T', ' ' } },
            { { 'L', ' ' }, { 'L', ' ' }, { 'L', 'L' } },
            { { 'J', 'J' }, { ' ', 'J' }, { ' ', 'J' } },
            { { 'S', 'S', ' ' }, { ' ', 'S', 'S' } },
            { { 'Z', 'Z', ' ' }, { ' ', 'Z', 'Z' } }
    };

    @Override
    public Shape createRandomShape(Random random) {
        int index = random.nextInt(SHAPES.length);
        return new Shape(deepCopy(SHAPES[index]), this);
    }

    @Override
    public Shape createShape(char[][] shapeArray) {
        return new Shape(deepCopy(shapeArray), this);
    }

    public char[][] deepCopy(char[][] original) {
        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
}

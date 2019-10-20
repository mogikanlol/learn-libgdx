package com.mogikanlol.game.core.entity;

import com.badlogic.gdx.math.Rectangle;

public class Border {

    private final int top;
    private final int bottom;
    private final int left;
    private final int right;

    private final Rectangle[] borders;

    public Border(int width, int height, int blockSize) {
        borders = new Rectangle[4];

        for (int i = 0; i < 4; i++) {
            borders[i] = new Rectangle();
        }

        for (int i = 0; i < borders.length; i++) {
            if (i % 2 == 0) {
                borders[i].setSize(width, blockSize);
            } else {
                borders[i].setSize(blockSize, height);
            }

            if (i < 2) {
                borders[i].setPosition(0, 0);
            } else if (i == 2) {
                borders[i].setPosition(0, height - blockSize);
            } else {
                borders[i].setPosition(width - blockSize, 0);
            }
        }
        this.top = blockSize;
        bottom = height - blockSize;
        left = blockSize;
        right = width - blockSize;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public Rectangle[] getBorders() {
        return borders;
    }
}

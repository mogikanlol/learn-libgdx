package com.mogikanlol.game.core.entity;

public class SnakeBlock {

    public int x;

    public int y;

    public int width;

    public int height;

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

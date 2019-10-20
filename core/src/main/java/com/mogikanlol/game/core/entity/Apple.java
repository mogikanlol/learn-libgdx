package com.mogikanlol.game.core.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Apple {

    public boolean eated;

    public int x;
    public int y;
    public int radius;

    public Apple(int blockSize) {
        radius = blockSize / 2;
        eated = true;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 getPosition() {
        return new Vector2(x, y);
    }
}

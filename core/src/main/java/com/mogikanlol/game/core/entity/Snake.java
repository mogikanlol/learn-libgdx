package com.mogikanlol.game.core.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private Vector2 vectorDir;
    private Direction currentDirection;
    private List<SnakeBlock> body;

    public Snake() {
        body = new ArrayList<>();
    }

    public Vector2 getVectorDir() {
        return vectorDir;
    }

    public List<SnakeBlock> getBody() {
        return body;
    }

    public SnakeBlock getHead() {
        return body.get(0);
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setVectorDir(Vector2 vectorDir) {
        this.vectorDir = vectorDir;
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }


}

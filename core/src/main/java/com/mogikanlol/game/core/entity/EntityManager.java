package com.mogikanlol.game.core.entity;

import com.mogikanlol.game.core.contants.GameConstants;

public class EntityManager {

    private final Snake snake;

    private final Apple apple;

    private final Border border;

    public EntityManager() {
        snake = new Snake();
        apple = new Apple(GameConstants.BLOCK_SIZE);
        border = new Border(GameConstants.WIDTH, GameConstants.HEIGHT, GameConstants.BLOCK_SIZE);
    }

    public Apple getApple() {
        return apple;
    }

    public Snake getSnake() {
        return snake;
    }

    public Border getBorder() {
        return border;
    }
}

package com.mogikanlol.game.core.controller;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mogikanlol.game.core.entity.Border;
import com.mogikanlol.game.core.contants.GameConstants;
import com.mogikanlol.game.core.entity.Apple;
import com.mogikanlol.game.core.entity.EntityManager;
import com.mogikanlol.game.core.entity.Snake;
import com.mogikanlol.game.core.entity.SnakeBlock;

import java.util.concurrent.ThreadLocalRandom;

public class AppleController {

    private final Apple apple;

    private final EntityManager entityManager;

    public AppleController(EntityManager entityManager) {
        this.apple = entityManager.getApple();
        this.entityManager = entityManager;
    }

    public void spawnApple() {
        if (apple.eated) {
            int blockSize = GameConstants.BLOCK_SIZE;
            Border border = entityManager.getBorder();
            Snake snake = entityManager.getSnake();

            int xRange = border.getRight() - border.getLeft();
            int yRange = border.getBottom() - border.getTop();

            float x = ThreadLocalRandom.current().nextInt(0, xRange / blockSize) * blockSize + blockSize;
            float y = ThreadLocalRandom.current().nextInt(0, yRange / blockSize) * blockSize + blockSize;

            Vector2 newPosition = new Vector2(x, y);

            for (SnakeBlock snakeBlock : snake.getBody()) {
                if (snakeBlock.x == newPosition.x && snakeBlock.y == newPosition.y) {
                    return;
                }
            }

            apple.setPosition((int) newPosition.x, (int) newPosition.y);
            apple.eated = false;
            System.out.println(newPosition);
        }
    }
}

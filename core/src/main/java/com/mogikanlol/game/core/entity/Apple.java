package com.mogikanlol.game.core.entity;

import com.badlogic.gdx.math.Vector2;
import com.mogikanlol.game.core.contants.GameConstants;
import com.mogikanlol.game.core.util.MVector2;

import java.util.concurrent.ThreadLocalRandom;

public class Apple {

    private boolean eaten;
    private MVector2 position;
    private int radius;

    public Apple() {
        radius = GameConstants.BLOCK_SIZE / 2;
        eaten = true;
        position = new MVector2();
    }

    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }

    public MVector2 getPosition() {
        return position;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void spawnApple(Border border, Snake snake) {
        if (eaten) {
            int blockSize = GameConstants.BLOCK_SIZE;

            int xRange = border.getRight() - border.getLeft();
            int yRange = border.getBottom() - border.getTop();

            float x = ThreadLocalRandom.current().nextInt(0, xRange / blockSize) * blockSize + blockSize;
            float y = ThreadLocalRandom.current().nextInt(0, yRange / blockSize) * blockSize + blockSize;

            Vector2 newPosition = new Vector2(x, y);

            for (SnakeBlock snakeBlock : snake.getBody()) {
                if (snakeBlock.getX() == newPosition.x && snakeBlock.getY() == newPosition.y) {
                    return;
                }
            }

            setPosition((int) newPosition.x, (int) newPosition.y);
            eaten = false;
        }
    }
}

package com.mogikanlol.game.core.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mogikanlol.game.core.entity.Border;
import com.mogikanlol.game.core.contants.GameConstants;
import com.mogikanlol.game.core.entity.Apple;
import com.mogikanlol.game.core.entity.EntityManager;
import com.mogikanlol.game.core.entity.Snake;
import com.mogikanlol.game.core.entity.SnakeBlock;

import java.util.List;

public class SnakeController {

    private final EntityManager entityManager;

    private final Snake snake;

    public SnakeController(EntityManager entityManager) {
        this.snake = entityManager.getSnake();
        this.entityManager = entityManager;
    }

    public void reset() {
        List<SnakeBlock> body = snake.getBody();
        int blockSize = GameConstants.BLOCK_SIZE;

        body.clear();

        int startX = blockSize * 5;
        int startY = blockSize * 5;

        SnakeBlock head = new SnakeBlock();
        head.setSize(blockSize - 1, blockSize - 1);
        head.setPosition(startX, startY);

        SnakeBlock bone = new SnakeBlock();
        bone.setSize(blockSize - 1, blockSize - 1);
        bone.setPosition(startX - blockSize, startY);

        SnakeBlock tail = new SnakeBlock();
        tail.setSize(blockSize - 1, blockSize - 1);
        tail.setPosition(startX - blockSize * 2, startY);

        snake.setVectorDir(new Vector2(blockSize, 0));
        snake.setCurrentDirection(Snake.Direction.RIGHT);

        body.add(head);
        body.add(bone);
        body.add(tail);
    }

    public void updateSnake() {
        int blockSize = GameConstants.BLOCK_SIZE;

        List<SnakeBlock> body = snake.getBody();
        Vector2 vectorDir = snake.getVectorDir();

        if (body.size() > 1) {
            SnakeBlock snakeBlock = new SnakeBlock();
            snakeBlock.setSize(blockSize - 1, blockSize - 1);
            snakeBlock.setPosition(body.get(0).x, body.get(0).y);

            body.add(0, snakeBlock);
            body.remove(body.size() - 1);
        }

        SnakeBlock head = body.get(0);

        head.x += vectorDir.x;
        head.y += vectorDir.y;

    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            setDirection(Snake.Direction.UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            setDirection(Snake.Direction.DOWN);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            setDirection(Snake.Direction.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            setDirection(Snake.Direction.RIGHT);
        }
    }

    public void checkCollisions() {
        Apple apple = entityManager.getApple();
        Border border = entityManager.getBorder();

        SnakeBlock snakeHead = snake.getHead();
        Vector2 applePosition = apple.getPosition();

        if (snakeHead.x >= border.getRight() || snakeHead.x < border.getLeft() ||
                snakeHead.y >= border.getBottom() || snakeHead.y < border.getTop()) {
            reset();
        }

        if (snake.getBody().size() > 4) {
            List<SnakeBlock> body = snake.getBody();
            SnakeBlock headPosition = snake.getHead();

            for (int i = 4; i < body.size(); i++) {
                SnakeBlock bodyBlock = body.get(i);
                if (headPosition.x == bodyBlock.x && headPosition.y == bodyBlock.y) {
                    reset();
                }
            }
        }

        if (snakeHead.x == applePosition.x && snakeHead.y == applePosition.y) {
            grow();
            apple.eated = true;
        }
    }

    private void grow() {
        int blockSize = GameConstants.BLOCK_SIZE;
        List<SnakeBlock> body = snake.getBody();
        Vector2 vectorDir = snake.getVectorDir();

        SnakeBlock last = body.get(body.size() - 1);

        SnakeBlock snakeBlock = new SnakeBlock();
        snakeBlock.setSize(blockSize - 1, blockSize - 1);
        snakeBlock.setPosition(last.x - (int) vectorDir.x, last.y - (int) vectorDir.y);

        body.add(snakeBlock);
    }

    private void setDirection(Snake.Direction direction) {

        Snake.Direction currentDirection = snake.getCurrentDirection();
        int blockSize = GameConstants.BLOCK_SIZE;

        switch (direction) {
            case UP:
                if (currentDirection != Snake.Direction.DOWN) {
                    snake.setVectorDir(new Vector2(0, blockSize));
                    snake.setCurrentDirection(Snake.Direction.UP);
                }
                break;
            case DOWN:
                if (currentDirection != Snake.Direction.UP) {
                    snake.setVectorDir(new Vector2(0, -blockSize));
                    snake.setCurrentDirection(Snake.Direction.DOWN);
                }
                break;
            case LEFT:
                if (currentDirection != Snake.Direction.RIGHT) {
                    snake.setVectorDir(new Vector2(-blockSize, 0));

                    snake.setCurrentDirection(Snake.Direction.LEFT);
                }
                break;
            case RIGHT:
                if (currentDirection != Snake.Direction.LEFT) {
                    snake.setVectorDir(new Vector2(blockSize, 0));
                    snake.setCurrentDirection(Snake.Direction.RIGHT);
                }
                break;
        }
    }
}

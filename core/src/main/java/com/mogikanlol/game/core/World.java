package com.mogikanlol.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mogikanlol.game.core.entity.*;
import com.mogikanlol.game.core.util.MVector2;

import java.util.List;

public class World {

    private final Snake snake;
    private final Apple apple;
    private final Border border;

    private final Renderer renderer;

    public World() {
        snake = new Snake();
        apple = new Apple();
        border = new Border();

        renderer = new Renderer();

        apple.spawnApple(border, snake);
        snake.reset();
    }

    public void update() {
        snake.updateSnake();

        checkCollisions();

        apple.spawnApple(border, snake);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        renderer.drawApple(shapeRenderer, apple);
        renderer.drawBorder(shapeRenderer, border);
        renderer.drawSnake(shapeRenderer, snake);
    }

    public void handleInput() {
        handleSnakeInput();
    }

    public void reset() {
        snake.reset();
        apple.spawnApple(border, snake);
    }

    private void handleSnakeInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            snake.setDirection(SnakeDirection.UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            snake.setDirection(SnakeDirection.DOWN);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            snake.setDirection(SnakeDirection.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            snake.setDirection(SnakeDirection.RIGHT);
        }
    }

    public void checkCollisions() {
        SnakeBlock snakeHead = snake.getHead();
        MVector2 applePosition = apple.getPosition();

        if (snakeHead.getX() >= border.getRight()
                || snakeHead.getX() < border.getLeft()
                || snakeHead.getY() >= border.getBottom()
                || snakeHead.getY() < border.getTop()) {
            reset();
        }

        if (snake.getBody().size() > 4) {
            List<SnakeBlock> body = snake.getBody();
            SnakeBlock headPosition = snake.getHead();

            for (int i = 4; i < body.size(); i++) {
                SnakeBlock bodyBlock = body.get(i);
                if (headPosition.getX() == bodyBlock.getX() && headPosition.getY() == bodyBlock.getY()) {
                    reset();
                }
            }
        }
        if (snakeHead.getX() == applePosition.getX() && snakeHead.getY() == applePosition.getY()) {
            snake.grow();
            apple.setEaten(true);
        }
    }
}

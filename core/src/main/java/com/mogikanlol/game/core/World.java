package com.mogikanlol.game.core;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mogikanlol.game.core.controller.AppleController;
import com.mogikanlol.game.core.controller.SnakeController;
import com.mogikanlol.game.core.entity.EntityManager;

public class World {

    private final EntityManager entityManager;
    private final Renderer renderer;
    private final SnakeController snakeController;
    private final AppleController appleController;

    public World() {
        entityManager = new EntityManager();

        renderer = new Renderer(entityManager);

        snakeController = new SnakeController(entityManager);
        appleController = new AppleController(entityManager);

        appleController.spawnApple();
        snakeController.reset();
    }

    public void update() {
        snakeController.updateSnake();

        snakeController.checkCollisions();

        appleController.spawnApple();
    }

    public void draw(ShapeRenderer shapeRenderer) {
        renderer.drawApple(shapeRenderer);
        renderer.drawBorder(shapeRenderer);
        renderer.drawSnake(shapeRenderer);
    }

    public void handleInput() {
        snakeController.handleInput();
    }

    public void reset() {
        snakeController.reset();
        appleController.spawnApple();
    }

}

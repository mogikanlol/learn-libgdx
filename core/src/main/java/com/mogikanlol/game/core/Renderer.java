package com.mogikanlol.game.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mogikanlol.game.core.entity.Apple;
import com.mogikanlol.game.core.entity.Border;
import com.mogikanlol.game.core.entity.EntityManager;
import com.mogikanlol.game.core.entity.Snake;
import com.mogikanlol.game.core.entity.SnakeBlock;

import java.util.List;

public class Renderer {

    private final EntityManager entityManager;

    public Renderer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void drawApple(ShapeRenderer renderer) {
        Apple apple = entityManager.getApple();
        if (!apple.eated) {

            renderer.setColor(Color.RED);

            // LigGdx draws circle with origin in x and y, so I have to add radius
            renderer.circle(apple.x + apple.radius, apple.y + apple.radius, apple.radius);
        }
    }

    public void drawSnake(ShapeRenderer renderer) {
        Snake snake = entityManager.getSnake();
        List<SnakeBlock> body = snake.getBody();

        for (int i = 0; i < body.size(); i++) {
            if (i == 0) {
                renderer.setColor(Color.YELLOW);
            } else {
                renderer.setColor(Color.GREEN);
            }
            SnakeBlock snakeBlock = body.get(i);

            renderer.rect(snakeBlock.x, snakeBlock.y, snakeBlock.width, snakeBlock.height);
        }
    }

    public void drawBorder(ShapeRenderer renderer) {
        Border border = entityManager.getBorder();
        Rectangle[] borders = border.getBorders();

        renderer.setColor(0, 50, 100, 1);
        for (Rectangle rect : borders) {
            renderer.rect(rect.x, rect.y, rect.width, rect.height);
        }
    }

}

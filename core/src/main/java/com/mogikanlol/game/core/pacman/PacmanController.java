package com.mogikanlol.game.core.pacman;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mogikanlol.game.core.util.MVector2;

public class PacmanController extends InputAdapter {

    private static final int BLOCK_SIZE = 40;

    private final Pacman pacman;

    private final Grid grid;

    public PacmanController(Pacman pacman, Grid grid) {
        this.pacman = pacman;
        this.grid = grid;
    }

    @Override
    public boolean keyDown(int keycode) {
        PacmanDirection direction = null;

        if (keycode == Input.Keys.W) {
            direction = PacmanDirection.UP;
        }
        else if (keycode == Input.Keys.S) {
            direction = PacmanDirection.DOWN;
        }
        else if (keycode == Input.Keys.A) {
            direction = PacmanDirection.LEFT;
        }
        else if (keycode == Input.Keys.D) {
            direction = PacmanDirection.RIGHT;
        }
        checkDirection(direction == null ? pacman.getDirection() : direction);

        return true;
    }

    private void checkDirection(PacmanDirection direction) {
        GridBlock nextElement = getNextElement(pacman.getPosition(), direction);

        if (direction == PacmanDirection.UP) {
            if (pacman.getPosition().getX() % 40 == 0) {
                if (nextElement.getType() != 1) {
                    pacman.setDirection(direction);
                    System.out.println("Set pacman direction to " + direction);
                }
            }
        }
        else if (direction == PacmanDirection.DOWN) {
            if (pacman.getPosition().getX() % 40 == 0) {
                if (nextElement.getType() != 1) {
                    pacman.setDirection(direction);
                    System.out.println("Set pacman direction to " + direction);
                }
            }
        }

        else if (direction == PacmanDirection.RIGHT) {
            if (pacman.getPosition().getY() % 40 == 0) {
                if (nextElement.getType() != 1) {
                    pacman.setDirection(direction);
                    System.out.println("Set pacman direction to " + direction);
                }
            }
        }

        else if (direction == PacmanDirection.LEFT) {
            if (pacman.getPosition().getY() % 40 == 0) {
                if (nextElement.getType() != 1) {
                    pacman.setDirection(direction);
                    System.out.println("Set pacman direction to " + direction);
                }
            }
        }
    }

    private GridBlock getNextElement(MVector2 position, PacmanDirection direction) {
        int x = position.getX();
        int y = position.getY();

        int xIndex = x / BLOCK_SIZE;
        int yIndex = y / BLOCK_SIZE;

        if (direction == PacmanDirection.UP) {
            return grid.at(yIndex + 1, xIndex);
        } else if (direction == PacmanDirection.DOWN) {
            return grid.at(yIndex - 1, xIndex);
        } else if (direction == PacmanDirection.RIGHT) {
            return grid.at(yIndex, xIndex + 1);
        } else if (direction == PacmanDirection.LEFT) {
            return grid.at(yIndex, xIndex - 1);
        }
        throw new RuntimeException();
    }
}

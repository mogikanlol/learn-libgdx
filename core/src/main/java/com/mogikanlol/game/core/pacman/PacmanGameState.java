package com.mogikanlol.game.core.pacman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mogikanlol.game.core.contants.GameConstants;
import com.mogikanlol.game.core.state.GameStateManager;
import com.mogikanlol.game.core.state.base.GameState;
import com.mogikanlol.game.core.state.base.GameStateName;
import com.mogikanlol.game.core.util.MVector2;

public class PacmanGameState extends GameState {
    private static final int BLOCK_SIZE = 40;

    private Pacman pacman;
    private Grid grid;
    private SpriteBatch spriteBatch;
    private TextureRegion[][] textureRegions;
    private PacmanController pacmanController;

    public PacmanGameState(GameStateManager gsm) {
        super(gsm);
        spriteBatch = new SpriteBatch();
        pacman = new Pacman();
        grid = new Grid();
        pacmanController = new PacmanController(pacman, grid);

        Gdx.input.setInputProcessor(pacmanController);

        Texture gridTexture = new Texture(Gdx.files.internal("pacman/tiles.png"));
        textureRegions = TextureRegion.split(gridTexture, 16, 16);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {

        GridBlock nextElement = getNextElement2(pacman.getPosition(), pacman.getDirection());

        if (nextElement.getType() != 1) {
            pacman.update(dt);
        }

        double delta = 0.2;

        if (pacman.getDirection() == PacmanDirection.UP) {
            if (nextElement.getType() == 1 && pacman.getPosition().getY() / 40f - pacman.getPosition().getY() / 40 < delta) {
                pacman.setPosition(pacman.getPosition().getX(), (pacman.getPosition().getY() / 40) * 40);
            }
        } else if (pacman.getDirection() == PacmanDirection.DOWN) {
            if (nextElement.getType() == 1 && pacman.getPosition().getY() / 40f - pacman.getPosition().getY() / 40 < delta) {
                pacman.setPosition(pacman.getPosition().getX(), ((pacman.getPosition().getY() / 40)) * 40);
            }
        } else if (pacman.getDirection() == PacmanDirection.RIGHT) {
            if (nextElement.getType() == 1 && pacman.getPosition().getX() / 40f - pacman.getPosition().getX() / 40 < delta) {
                pacman.setPosition((pacman.getPosition().getX() / 40) * 40, pacman.getPosition().getY());
            }
        } else if (pacman.getDirection() == PacmanDirection.LEFT) {
            if (nextElement.getType() == 1 && pacman.getPosition().getX() / 40f - pacman.getPosition().getX() / 40 < delta) {
                pacman.setPosition((pacman.getPosition().getX() / 40) * 40, pacman.getPosition().getY());
            }
        }

    }

    @Override
    public void draw() {
        spriteBatch.begin();

        drawGrid();

        spriteBatch.draw(
                pacman.getCurrentFrame(),
                pacman.getPosition().getX(),
                pacman.getPosition().getY(),
                BLOCK_SIZE,
                BLOCK_SIZE
        );

        spriteBatch.end();
    }

    private void drawGrid() {
        for (int row = 0; row < GameConstants.HEIGHT / BLOCK_SIZE; row++) {
            for (int col = 0; col < GameConstants.WIDTH / BLOCK_SIZE; col++) {
                GridBlock block = grid.getGrid()[row][col];
                MVector2 position = block.getPosition();
                if (block.getType() == 0) {
                    spriteBatch.draw(
                            textureRegions[0][1],
                            position.getX(),
                            position.getY(),
                            BLOCK_SIZE,
                            BLOCK_SIZE
                    );
                } else if (block.getType() == 1) {
                    spriteBatch.draw(
                            textureRegions[0][0],
                            position.getX(),
                            position.getY(),
                            BLOCK_SIZE,
                            BLOCK_SIZE
                    );
                }
            }
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.setState(GameStateName.EXIT);
        }

//        PacmanDirection direction = null;
//
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            direction = PacmanDirection.UP;
//        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            direction = PacmanDirection.DOWN;
//        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            direction = PacmanDirection.LEFT;
//        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            direction = PacmanDirection.RIGHT;
//        }
//
//        checkDirection(direction == null ? pacman.getDirection() : direction);
    }

    private void checkDirection(PacmanDirection direction) {
        GridBlock nextElement = getNextElement(pacman.getPosition(), direction);

        if (direction == PacmanDirection.UP) {
            if (pacman.getPosition().getX() % 40 == 0) {
                if (nextElement.getType() != 1) {
                    pacman.setDirection(direction);
                }
            }
        } else if (direction == PacmanDirection.DOWN) {
            if (pacman.getPosition().getX() % 40 == 0) {
                if (nextElement.getType() != 1) {
                    pacman.setDirection(direction);
                }
            }
        } else if (direction == PacmanDirection.RIGHT) {
            if (pacman.getPosition().getY() % 40 == 0) {
                if (nextElement.getType() != 1) {
                    pacman.setDirection(direction);
                }
            }
        } else if (direction == PacmanDirection.LEFT) {
            if (pacman.getPosition().getY() % 40 == 0) {
                if (nextElement.getType() != 1) {
                    pacman.setDirection(direction);
                }
            }
        }
    }

    private GridBlock getNextElement2(MVector2 position, PacmanDirection direction) {
        int x = position.getX();
        int y = position.getY();

        int xIndex = (int) Math.ceil(x / (float) BLOCK_SIZE);
        int yIndex = (int) Math.ceil(y / (float) BLOCK_SIZE);


        if (direction == PacmanDirection.UP) {
            return grid.at(yIndex, xIndex);
        } else if (direction == PacmanDirection.DOWN) {
            return grid.at(yIndex - 1, xIndex);
        } else if (direction == PacmanDirection.RIGHT) {
            return grid.at(yIndex, xIndex);
        } else if (direction == PacmanDirection.LEFT) {
            return grid.at(yIndex, xIndex - 1);
        }
        throw new RuntimeException();
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

    @Override
    public void dispose() {

    }

    @Override
    public void reset() {
        pacman.reset();
    }
}

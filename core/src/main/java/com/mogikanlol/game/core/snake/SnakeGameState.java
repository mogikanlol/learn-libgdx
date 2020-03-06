package com.mogikanlol.game.core.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mogikanlol.game.core.state.base.GameState;
import com.mogikanlol.game.core.state.base.GameStateManager;
import com.mogikanlol.game.core.state.base.GameStateName;

public class SnakeGameState extends GameState {

    private static final float TIME_STEP = 0.12f;

    private SnakeGameWorld world;
    private ShapeRenderer renderer;
    private float elapsedTime;

    public SnakeGameState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        world = new SnakeGameWorld();
        renderer = new ShapeRenderer();
    }

    @Override
    public void update(float dt) {
        if (elapsedTime > TIME_STEP) {
            world.update();

            elapsedTime -= TIME_STEP;
        }
        updateElapsedTime(dt);
    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        world.draw(renderer);

        renderer.end();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.setState(GameStateName.PAUSE);
        }

        world.handleInput();
    }

    @Override
    public void reset() {
        world.reset();
    }

    @Override
    public void dispose() {

    }

    private void updateElapsedTime(float delta) {
        this.elapsedTime += delta;
    }
}

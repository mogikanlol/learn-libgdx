package com.mogikanlol.game.core.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mogikanlol.game.core.World;
import com.mogikanlol.game.core.state.base.GameState;
import com.mogikanlol.game.core.state.base.GameStateManager;
import com.mogikanlol.game.core.state.base.GameStateName;

public class PlayState extends GameState {

    private World world;

    private ShapeRenderer renderer;

    private final float timeStep = 0.12f;

    private float elapsedTime;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        world = new World();
        renderer = new ShapeRenderer();
    }

    @Override
    public void update(float dt) {
        if (elapsedTime > timeStep) {
            world.update();

            elapsedTime -= timeStep;
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

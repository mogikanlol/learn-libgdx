package com.mogikanlol.game.core.state.base;

import com.mogikanlol.game.core.state.GameStateManager;

public abstract class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void init();

    public abstract void update(float dt);

    public abstract void draw();

    public abstract void handleInput();

    public abstract void dispose();

    public abstract void reset();

}

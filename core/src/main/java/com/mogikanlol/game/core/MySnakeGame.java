package com.mogikanlol.game.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mogikanlol.game.core.state.base.GameStateManager;
import com.mogikanlol.game.core.state.base.GameStateName;

public class MySnakeGame extends ApplicationAdapter {

    private GameStateManager gsm;

    @Override
    public void create() {
        gsm = new GameStateManager();
        gsm.setState(GameStateName.MENU);
    }

    @Override
    public void render() {

        gsm.handleInput();

        gsm.update(Gdx.graphics.getDeltaTime());

        gsm.draw();
    }
}

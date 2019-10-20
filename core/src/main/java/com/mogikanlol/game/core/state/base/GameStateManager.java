package com.mogikanlol.game.core.state.base;

import com.badlogic.gdx.Gdx;
import com.mogikanlol.game.core.state.MenuState;
import com.mogikanlol.game.core.state.PauseState;
import com.mogikanlol.game.core.state.PlayState;

public class GameStateManager {

    private GameState activeState;

    private PlayState playState;

    private PauseState pauseState;

    private MenuState menuState;

    public GameStateManager() {
        menuState = new MenuState(this);
        playState = new PlayState(this);
        pauseState = new PauseState(this);
    }

    public void setState(GameStateName name) {
        if (name == GameStateName.PLAY) {
            this.activeState = playState;
        }
        if (name == GameStateName.PAUSE) {
            activeState = pauseState;
        }
        if (name == GameStateName.MENU) {
            activeState = menuState;

            playState.reset();
        }
        if (name == GameStateName.EXIT) {
            Gdx.app.exit();
        }
    }

    public void reset() {
        activeState.reset();
    }

    public void update(float dt) {
        activeState.update(dt);
    }

    public void draw() {
        activeState.draw();
    }

    public void handleInput() {
        activeState.handleInput();
    }
}

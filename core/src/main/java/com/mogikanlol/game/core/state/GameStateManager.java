package com.mogikanlol.game.core.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.mogikanlol.game.core.pacman.PacmanGameState;
import com.mogikanlol.game.core.pointandclick.PointAndClickGameState;
import com.mogikanlol.game.core.state.MenuState;
import com.mogikanlol.game.core.state.PauseState;
import com.mogikanlol.game.core.snake.SnakeGameState;
import com.mogikanlol.game.core.state.base.GameState;
import com.mogikanlol.game.core.state.base.GameStateName;

import java.util.EnumMap;
import java.util.Map;

public class GameStateManager {

    private GameState activeState;
    private SnakeGameState snakeGameState;
    private PauseState pauseState;
    private MenuState menuState;
    private PacmanGameState pacmanGameState;

    private Map<GameStateName, GameState> gameStates;

    private PointAndClickGameState pointAndClickGameState;

    public GameStateManager() {
        menuState = new MenuState(this);
        snakeGameState = new SnakeGameState(this);
        pauseState = new PauseState(this);
        pacmanGameState = new PacmanGameState(this);

        pointAndClickGameState = new PointAndClickGameState(this);

        gameStates = new EnumMap<>(GameStateName.class);
        gameStates.put(GameStateName.MENU, menuState);
        gameStates.put(GameStateName.PLAY_SNAKE_GAME, snakeGameState);
        gameStates.put(GameStateName.PAUSE, pauseState);
        gameStates.put(GameStateName.PLAY_PACMAN_GAME, pacmanGameState);
        gameStates.put(GameStateName.POINT_AND_CLICK, pointAndClickGameState);
    }

    public void setState(GameStateName name) {
        this.activeState = gameStates.get(name);

        if (name == GameStateName.MENU) {
            activeState = menuState;
            snakeGameState.reset();
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        activeState.draw();
    }

    public void handleInput() {
        activeState.handleInput();
    }
}

package com.mogikanlol.game.core.pointandclick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mogikanlol.game.core.contants.GameConstants;
import com.mogikanlol.game.core.state.GameStateManager;
import com.mogikanlol.game.core.state.base.GameState;

public class PointAndClickGameState extends GameState {

    private Texture background;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    private TextureRegion[] ballTextures = new TextureRegion[10];

    private Player player;

    private Animation<TextureRegion> ballAnimation;
    private float stateTime;

    private boolean shouldPlayBallAnimation;

    public PointAndClickGameState(GameStateManager gsm) {
        super(gsm);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        background = new Texture(Gdx.files.internal("point-and-click/background.jpg"));

        player = new Player();
        player.x = 200;
        player.y = 100;
        player.width = 20;
        player.height = 20;

        Texture ballAnimationTexture = new Texture(Gdx.files.internal("point-and-click/qwe.png"));

        for (int i = 0; i < 5; i++) {
            ballTextures[i] = new TextureRegion(ballAnimationTexture, i * 200, 240, 200, 310);
        }

            ballTextures[5] = ballTextures[4];
            ballTextures[6] = ballTextures[3];
            ballTextures[7] = ballTextures[2];
            ballTextures[8] = ballTextures[1];
            ballTextures[9] = ballTextures[0];

        ballTextures[0] = new TextureRegion(ballAnimationTexture, 0, 240, 200, 310);
        ballAnimation = new Animation<>(0.125f, ballTextures);
        stateTime = 0f;
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw() {

        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);
        if (!shouldPlayBallAnimation) {
            spriteBatch.draw(ballTextures[0], 400, 60, 100, 150);
        }
        if (shouldPlayBallAnimation) {
            stateTime += Gdx.graphics.getDeltaTime();
            TextureRegion keyFrame = ballAnimation.getKeyFrame(stateTime);
            spriteBatch.draw(keyFrame, 400, 60, 100, 150);
        }
        if (ballAnimation.isAnimationFinished(stateTime) && shouldPlayBallAnimation) {
            shouldPlayBallAnimation = false;
            stateTime = 0f;
        }
        spriteBatch.end();

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.BLACK);
//        shapeRenderer.rect(player.x, player.y, player.width, player.height);
//        shapeRenderer.end();
    }

    @Override
    public void handleInput() {
        int x = Gdx.input.getX();
        int y = GameConstants.HEIGHT - Gdx.input.getY();

        if (x > 400 && x < 500 && y > 60 && y < 130) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                shouldPlayBallAnimation = true;
            }
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void reset() {

    }
}

class Player {
    public int x;
    public int y;
    public int width;
    public int height;
}

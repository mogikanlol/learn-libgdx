package com.mogikanlol.game.core.pacman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mogikanlol.game.core.util.MVector2;

public class Pacman {

    private static final int FRAME_COLS = 3, FRAME_ROWS = 4;

    private static final int VELOCITY_VALUE = 5;

    private MVector2 vectorDir;
    private Texture texture;
    private MVector2 position;
    private PacmanDirection direction;

    private Animation<TextureRegion> animationRight;
    private Animation<TextureRegion> animationLeft;
    private Animation<TextureRegion> animationUp;
    private Animation<TextureRegion> animationDown;
    private Animation<TextureRegion> currentAnimation;
    private TextureRegion currentFrame;

    float stateTime;

    public Pacman() {
        texture = new Texture(Gdx.files.internal("pacman/pacman.png"));

        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth() / FRAME_COLS,
                texture.getHeight() / FRAME_ROWS);

        TextureRegion[] rightFrames = new TextureRegion[FRAME_COLS];
        TextureRegion[] leftFrames = new TextureRegion[FRAME_COLS];
        TextureRegion[] upFrames = new TextureRegion[FRAME_COLS];
        TextureRegion[] downFrames = new TextureRegion[FRAME_COLS];

        System.arraycopy(tmp[0], 0, rightFrames, 0, 3);
        System.arraycopy(tmp[1], 0, leftFrames, 0, 3);
        System.arraycopy(tmp[2], 0, upFrames, 0, 3);
        System.arraycopy(tmp[3], 0, downFrames, 0, 3);

        animationRight = new Animation<>(0.125f, rightFrames);
        animationLeft = new Animation<>(0.125f, leftFrames);
        animationUp = new Animation<>(0.125f, upFrames);
        animationDown = new Animation<>(0.125f, downFrames);
        currentAnimation = animationRight;
        stateTime = 0f;

        vectorDir = new MVector2(VELOCITY_VALUE, 0);
        position = new MVector2(40, 40);
        direction = PacmanDirection.RIGHT;
    }

    public void reset() {
        position.setX(40);
        position.setY(40);

        vectorDir.setX(VELOCITY_VALUE);
        vectorDir.setY(0);
        currentAnimation = animationRight;
    }

    public void setDirection(PacmanDirection direction) {
        if (direction == PacmanDirection.UP) {
            currentAnimation = animationUp;
            vectorDir.setX(0);
            vectorDir.setY(VELOCITY_VALUE);
        } else if (direction == PacmanDirection.DOWN) {
            currentAnimation = animationDown;
            vectorDir.setX(0);
            vectorDir.setY(-VELOCITY_VALUE);
        } else if (direction == PacmanDirection.LEFT) {
            currentAnimation = animationLeft;
            vectorDir.setX(-VELOCITY_VALUE);
            vectorDir.setY(0);
        } else if (direction == PacmanDirection.RIGHT) {
            currentAnimation = animationRight;
            vectorDir.setX(VELOCITY_VALUE);
            vectorDir.setY(0);
        }
        this.direction = direction;
    }

    public void update(float dt) {
        int newX = position.getX() + vectorDir.getX();
        int newY = position.getY() + vectorDir.getY();

        position.setX(newX);
        position.setY(newY);

        stateTime += dt;
        currentFrame = currentAnimation.getKeyFrame(stateTime, true);
    }

    public MVector2 getPosition() {
        return position;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public PacmanDirection getDirection() {
        return direction;
    }

    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }

    public int getTop() {
        return position.getY() + 40;
    }
    public int getBottom() {
        return position.getY();
    }
    public int getRight() {
        return position.getX() + 40;
    }
    public int getLeft() {
        return position.getX();
    }
}

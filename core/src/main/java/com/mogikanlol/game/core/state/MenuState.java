package com.mogikanlol.game.core.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mogikanlol.game.core.state.base.GameState;
import com.mogikanlol.game.core.state.base.GameStateName;

import java.util.ArrayList;
import java.util.List;

public class MenuState extends GameState {

    private List<MenuItem> menuItems;

    private SpriteBatch batch;

    private BitmapFont font;

    private GlyphLayout glyphLayout;

    private final static int BUTTON_HEIGHT = 64;

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public MenuState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        batch = new SpriteBatch();
        menuItems = new ArrayList<>();
        font = new BitmapFont();
        glyphLayout = new GlyphLayout();

        menuItems.add(new MenuItem("Play Snake Game", () -> gsm.setState(GameStateName.PLAY_SNAKE_GAME)));
        menuItems.add(new MenuItem("Play Pacman Game", () -> gsm.setState(GameStateName.PLAY_PACMAN_GAME)));
        menuItems.add(new MenuItem("Credits"));
        menuItems.add(new MenuItem("Exit", () -> gsm.setState(GameStateName.EXIT)));

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        int margin = 16;

        int totalHeight = (BUTTON_HEIGHT + margin) * menuItems.size();
        int cursorY = 0;

        int buttonWidth = width / 3;

        cursorY = totalHeight - (BUTTON_HEIGHT + margin);

        Color defaultColor = new Color(0.4f, 0.4f, 0.5f, 1f);

        for (MenuItem menuItem : menuItems) {

            float bx = (float) ((width * 0.5) - (buttonWidth * 0.5));
            float by = (float) ((height * 0.5) - (totalHeight * 0.5)) + cursorY;
            menuItem.setX(bx);
            menuItem.setY(by);
            menuItem.setWidth(buttonWidth);
            menuItem.setHeight(BUTTON_HEIGHT);
            menuItem.setColor(defaultColor);

            cursorY = cursorY - (BUTTON_HEIGHT + margin);
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int height = Gdx.graphics.getHeight();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Color hoveredColor = new Color(0.8f, 0.8f, 0.9f, 1f);
        for (MenuItem menuItem : menuItems) {

            float bx = menuItem.getX();
            float by = menuItem.getY();

            int mx = Gdx.input.getX();
            int my = height - Gdx.input.getY();

            boolean hovered = mx > bx && mx < bx + menuItem.getWidth() &&
                    my > by && my < by + BUTTON_HEIGHT;

            if (hovered) {
                shapeRenderer.setColor(hoveredColor);
            } else {
                shapeRenderer.setColor(menuItem.getColor());
            }
            shapeRenderer.rect(
                    bx,
                    by,
                    menuItem.getWidth(),
                    BUTTON_HEIGHT
            );

            if (hovered && Gdx.input.justTouched()) {
                menuItem.getOnClick().run();
            }

        }
        shapeRenderer.end();

        boolean drawCenterLine = false;
        if (drawCenterLine) {
            drawMenuItemCenterLine();
        }

        batch.begin();
        for (MenuItem menuItem : menuItems) {
            menuItem.draw(batch);
        }
        batch.end();
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void dispose() {

    }

    @Override
    public void reset() {

    }

    private void drawMenuItemCenterLine() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (MenuItem menuItem : menuItems) {
            shapeRenderer.line(
                    0,
                    (float) (menuItem.getY() + menuItem.getHeight() * 0.5),
                    Gdx.graphics.getWidth(),
                    (float) (menuItem.getY() + menuItem.getHeight() * 0.5),
                    Color.OLIVE,
                    Color.CORAL
            );
        }
        shapeRenderer.end();
    }
}

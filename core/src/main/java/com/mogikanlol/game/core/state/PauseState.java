package com.mogikanlol.game.core.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class PauseState extends GameState {

    private List<MenuItem> menuItems;

    private BitmapFont font;

    private SpriteBatch batch;

    private GlyphLayout glyphLayout;

    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    private static final int BUTTON_HEIGHT = 64;

    private int selectedIndex = 0;

    public PauseState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        font = new BitmapFont();
        batch = new SpriteBatch();
        glyphLayout = new GlyphLayout();
        menuItems = new ArrayList<>();

        menuItems.add(new MenuItem("Resume", () -> gsm.setState(GameStateName.PLAY_SNAKE_GAME)));
        menuItems.add(new MenuItem("Go to Menu", () -> gsm.setState(GameStateName.MENU)));
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        int margin = 16;

        int totalHeight = (BUTTON_HEIGHT + margin) * menuItems.size();
        int cursorY = 0;

        int buttonWidth = width / 3;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        cursorY = totalHeight - (BUTTON_HEIGHT + margin);
        for (MenuItem menuItem : menuItems) {

            Color color = new Color(0.4f, 0.4f, 0.5f, 1f);
            float bx = (float) ((width * 0.5) - (buttonWidth * 0.5));
            float by = (float) ((height * 0.5) - (totalHeight * 0.5)) + cursorY;

            int mx = Gdx.input.getX();
            int my = height - Gdx.input.getY();

            boolean hovered = mx > bx && mx < bx + buttonWidth &&
                    my > by && my < by + BUTTON_HEIGHT;
            if (hovered) {
                color.set(0.8f, 0.8f, 0.9f, 1f);
            }
            shapeRenderer.setColor(color);
            shapeRenderer.rect(
                    bx,
                    by,
                    buttonWidth,
                    BUTTON_HEIGHT);

            if (hovered && Gdx.input.justTouched()) {
                menuItem.onClick.run();
            }

            cursorY = cursorY - (BUTTON_HEIGHT + margin);
        }
        shapeRenderer.end();
        batch.begin();

        cursorY = totalHeight - (BUTTON_HEIGHT + margin);
        for (MenuItem menuItem : menuItems) {
            float bx = (float) ((width * 0.5) - (buttonWidth * 0.5));
            float by = (float) ((height * 0.5) - (totalHeight * 0.5)) + cursorY;


            glyphLayout.setText(font, menuItem.title);
            float textW = glyphLayout.width;
            float textH = glyphLayout.height;

            font.setColor(0, 0, 0, 1);
            float textX = (float) (width * 0.5 - textW * 0.5);
            float textY = (float) (by  + BUTTON_HEIGHT / 2);
            font.draw(batch, glyphLayout, textX, textY);
            cursorY = cursorY - (BUTTON_HEIGHT + margin);
        }
        batch.end();
    }

    @Override
    public void reset() {

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            this.selectedIndex++;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            this.selectedIndex--;
        }
        if (this.selectedIndex == menuItems.size()) {
            this.selectedIndex = 0;
        } else if (this.selectedIndex < 0) {
            this.selectedIndex = menuItems.size() - 1;
        }

//        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
//            gsm.setState(GameStateName.PLAY);
//        }

//        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
//            gsm.setState(menuItems.get(selectedIndex).stateName);
//        }
    }

    @Override
    public void dispose() {

    }

}

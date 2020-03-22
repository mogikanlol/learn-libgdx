package com.mogikanlol.game.core.state;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mogikanlol.game.core.contants.GameConstants;

public class MenuItem {

    private final static int BUTTON_HEIGHT = 64;

    private String title;

    private Runnable onClick;

    private float x;
    private float y;
    private int width;
    private int height;
    private Color color;

    private GlyphLayout glyphLayout;
    private BitmapFont font;

    public MenuItem(String title) {
        this.title = title;
        onClick = () -> {
        };

        init();
    }

    public MenuItem(String title, Runnable onClick) {
        this.title = title;
        this.onClick = onClick;

        init();
    }

    private void init() {
        font = new BitmapFont();
        font.setColor(0,0,0,1);
        glyphLayout = new GlyphLayout(font, title);
    }

    public void draw(SpriteBatch batch) {
        float textW = glyphLayout.width;
        float textH = glyphLayout.height;

        float textX = (float) (GameConstants.WIDTH * 0.5 - textW * 0.5);
        float textY = (float) (this.y + BUTTON_HEIGHT * 0.5 + textH * 0.5);
        font.draw(batch, glyphLayout, textX, textY);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Runnable getOnClick() {
        return onClick;
    }

    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public GlyphLayout getGlyphLayout() {
        return glyphLayout;
    }

    public void setGlyphLayout(GlyphLayout glyphLayout) {
        this.glyphLayout = glyphLayout;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }
}

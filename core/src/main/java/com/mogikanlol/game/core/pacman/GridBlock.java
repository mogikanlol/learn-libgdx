package com.mogikanlol.game.core.pacman;

import com.mogikanlol.game.core.util.MVector2;

public class GridBlock {

    private MVector2 position;

    private int type;

    public GridBlock(int x, int y, int type) {
        position = new MVector2(x, y);
        this.type = type;
    }

    public MVector2 getPosition() {
        return position;
    }

    public int getType() {
        return type;
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

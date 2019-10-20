package com.mogikanlol.game.core.state;

import com.mogikanlol.game.core.state.base.GameStateName;

public class MenuItem {
    public String title;

    public Runnable onClick;

    public MenuItem(String title) {
        this.title = title;
        onClick = () -> {};
    }

    public MenuItem(String title, Runnable onClick) {
        this.title = title;
        this.onClick = onClick;
    }
}

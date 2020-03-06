package com.mogikanlol.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mogikanlol.game.core.MyApplication;
import com.mogikanlol.game.core.contants.GameConstants;

public class DesktopLauncher {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = GameConstants.WIDTH;
        config.height = GameConstants.HEIGHT;
        config.resizable = false;
        config.foregroundFPS = 60;

        new LwjglApplication(new MyApplication(), config);
    }

}

package com.mr_neez.framework.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class GameScreen extends BaseScreen {

    private Stage gameHud;

    public Stage getGameHud() {
        if (gameHud == null)
            gameHud = new Stage();
        return gameHud;
    }
}

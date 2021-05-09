package com.mr_neez.framework;

import com.badlogic.gdx.Screen;
import com.mr_neez.framework.starfishGame.levels.Level1;

public class Game extends com.badlogic.gdx.Game {
    /**
     * Called when the {@link Application} is first created.
     */
    @Override
    public void create () {
        // store the static ref
        Ref.game = this;
        setScreen(new Level1());
    }
}

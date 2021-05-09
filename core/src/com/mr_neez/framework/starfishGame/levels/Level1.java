package com.mr_neez.framework.starfishGame.levels;



import com.mr_neez.framework.Ref;
import com.mr_neez.framework.game.entities.Ocean;
import com.mr_neez.framework.game.entities.Turtle;
import com.mr_neez.framework.screens.LevelScreen;

public class Level1 extends LevelScreen {

    Turtle turtle;
    Ocean world;

    @Override
    protected void initialize() {
        super.initialize();

        Ocean world = new Ocean(0, 0, getMainStage());
        Turtle turtle = new Turtle(50, 50, getMainStage());
    }
}

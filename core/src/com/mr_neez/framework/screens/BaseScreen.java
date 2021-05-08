package com.mr_neez.framework.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseScreen extends ScreenAdapter {
    private Stage mainStage;

    public BaseScreen() {
        initialize();
    }


    @Override
    public void render(float delta) {
        // clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        // act and draw
        getMainStage().act();
        getMainStage().draw();
    }


    protected Stage getMainStage() {
        if (mainStage == null) {
           mainStage = new Stage();
        }
        return  mainStage;
    }


    abstract protected void initialize();
    abstract protected void update(float delta);
}

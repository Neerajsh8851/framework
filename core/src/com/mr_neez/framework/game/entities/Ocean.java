package com.mr_neez.framework.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mr_neez.framework.Ref;
import com.mr_neez.framework.Res;
import com.mr_neez.framework.entity.Entity;

public class Ocean extends Entity {
    TextureRegion texture;

    public Ocean(float x, float y, Stage stage) {
        super(x, y, stage);
        stage.addActor(this);

        //load texture
        texture = new TextureRegion(Res.loadTexture("water.jpg"));
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        draw(batch, texture);
    }
}

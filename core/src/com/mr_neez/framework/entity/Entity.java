package com.mr_neez.framework.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Entity extends Actor {

    public Entity(float x, float y, Stage stage) {
        setPosition(x, y);
        stage.addActor(this);
    }

    protected void draw(Batch batch, TextureRegion texture) {
        // tint
        batch.setColor(getColor());

        batch.draw(
                texture,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );
    }

}

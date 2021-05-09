package com.mr_neez.framework.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mr_neez.framework.entity.Entity;
import com.mr_neez.framework.entity.plugins.AnimationPlugin;
import com.mr_neez.framework.entity.plugins.PhysicsPlugin;

public class Turtle extends Entity {
    private final AnimationPlugin animPG;
    private final PhysicsPlugin phyPG;


    public Turtle(float x, float y, Stage stage) {
        super(x, y, stage);

        animPG = new AnimationPlugin(this);

        // for physics
        phyPG = new PhysicsPlugin(this);
        phyPG.setAcceleration(400);
        phyPG.setMaxSpeed(100);
        phyPG.setDeceleration(300);


        animPG.createAnimationFromImages(
                1/10f,
                Animation.PlayMode.LOOP,
                "turtle-1.png", "turtle-2.png", "turtle-3.png", "turtle-4.png", "turtle-5.png", "turtle-6.png"
        );

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        //update animation
        animPG.animationUpdate(delta);

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
           phyPG.accelerateAtAngle(90);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            phyPG.accelerateAtAngle(270);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            phyPG.accelerateAtAngle(0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            phyPG.accelerateAtAngle(180);
        }

        if(phyPG.isMoving()) {
            setRotation(phyPG.getMotionAngle());
            animPG.setAnimationPause(false);
        } else {
            animPG.setAnimationPause(true);
            animPG.reset();
        }

        if(Gdx.input.isTouched()) {
            phyPG.accelerateAtAngle(0);
        }

        phyPG.applyPhysics(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        draw(batch, animPG.currentKeyFrame());
    }
}

package com.mr_neez.framework.entity.plugins;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mr_neez.framework.entity.Entity;

public class PhysicsPlugin {

    private Vector2 velocity;
    private Vector2 acceleration;
    private float acc;
    private float dec;
    private float maxSpeed;

    private Entity entity;

    public PhysicsPlugin(Entity entity) {
        this.entity = entity;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
    }

    public   void accelerateAtAngle(float angle) {
        acceleration = new Vector2(acc * MathUtils.cosDeg(angle), acc * MathUtils.sinDeg(angle) );
    }


    public boolean isMoving() {
        return velocity.len2() > 0;
    }

    public float getSpeed() {
        return velocity.len();
    }

    public void setSpeed(float speed) {
        velocity.setLength(speed);
    }

    public void setAcceleration(float acc) {
        this.acc = acc;
    }

    public void setDeceleration(float dec) {
        this.dec = dec;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getMotionAngle() {
        return velocity.angleDeg();
    }

    public void applyPhysics(float delta) {
        float speed;
        if (acceleration.len2() != 0) {
            velocity.add(delta * acceleration.x, delta * acceleration.y);
            speed = getSpeed();
        } else {
            speed = getSpeed();
            speed = -delta * dec;
        }

        speed = MathUtils.clamp(speed, 0, maxSpeed);
        setSpeed(speed);

        // update object position
        entity.moveBy(velocity.x * delta, velocity.y * delta);

        // reset Acc
        acceleration.set(0, 0);
    }
}

package com.mr_neez.framework.Actors.anime;

import com.badlogic.gdx.math.Vector2;

public class DAnimeActor extends AnimeActor{
    private Vector2 velocity;
    private Vector2 acceleration;
    private float acc;
    private float dec;
    private float maxSpeed;

    public DAnimeActor(float maxSpeed, float acceleration, float deceleration ) {
        acc = acceleration;
        dec = deceleration;
        this.maxSpeed = maxSpeed;
    }
}

package com.mr_neez.framework.entity.plugins;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mr_neez.framework.Res;
import com.mr_neez.framework.entity.Entity;

public class AnimationPlugin {

    private Animation<TextureRegion> animation;
    private float stateTime;
    private boolean animationPause;
    private Entity entity;


    /**
     * Stores the reference to the entity
     * @param entity
     */
    public AnimationPlugin(Entity entity) {
        this.entity = entity;
    }

    public void setAnimation(Animation<TextureRegion> anim) {
        TextureRegion texture = anim.getKeyFrame(0);
        entity.setSize(texture.getRegionWidth(), texture.getRegionHeight());
        entity.setOrigin(texture.getRegionWidth()/2f, texture.getRegionHeight()/2f);
        this.animation = anim;
    }


    // ----------------------------------
    // ------CREATE ANIMATION------------
    //-----------------------------------

    protected void createAnimationFromImages(float frameDuration,  String... names) {
        createAnimationFromImages(frameDuration, Animation.PlayMode.NORMAL,  names);
    }

    public void createAnimationFromImages(float frameDuration,  Animation.PlayMode playMode, String... names) {
        Array<TextureRegion> textureArray = new Array<TextureRegion>();

        for (String name : names) {
            TextureRegion region = new TextureRegion(Res.loadTexture(name));
            textureArray.add(region);
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>( frameDuration, textureArray );
        anim.setPlayMode(playMode);
        setAnimation(anim);
    }

    public void createAnimationFromSheet(float frameDuration, String name, int rows, int cols) {
        createAnimationFromSheet(frameDuration, Animation.PlayMode.NORMAL, name, rows, cols);
    }

    public void createAnimationFromSheet(float frameDuration, Animation.PlayMode playMode, String name, int rows, int cols) {

        Texture texture = Res.getTextureByName(name);
        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;
        TextureRegion[][] regions = TextureRegion.split(Res.getTextureByName(name), frameWidth, frameHeight);

        Array<TextureRegion> textureArray = new Array();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                textureArray.add(regions[r][c]);
            }
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>( frameDuration, textureArray );
        anim.setPlayMode(playMode);
        setAnimation(anim);
    }


    public void animationUpdate(float delta) {
        if (!animationPause)
        stateTime += delta;
    }

    public TextureRegion currentKeyFrame() {
        return animation.getKeyFrame(stateTime);
    }

    public void setAnimationPause(boolean pause) {
        animationPause = pause;
    }

    public boolean isAnimationPause() {
        return animationPause;
    }

    public void reset() {
        stateTime = 0;
    }
}

package com.mr_neez.framework.Actors.anime;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mr_neez.framework.Actors.BaseActor;
import com.mr_neez.framework.Res;

public class AnimeActor extends BaseActor {

    private Animation<TextureRegion> animation;
    private float elapseTime;
    private boolean animationPause;


    public void setAnimation(Animation<TextureRegion> anim) {
        this.animation = anim;
    }

    // ----------------------------------
    // ------CREATE ANIMATION------------
    //-----------------------------------

    protected void createAnimationFromImages(float frameDuration,  String... names) {
        createAnimationFromImages(frameDuration, Animation.PlayMode.NORMAL,  names);
    }

    protected void createAnimationFromImages(float frameDuration,  Animation.PlayMode playMode, String... names) {
        Array textureArray = new Array();

        for (String name : names) {
            TextureRegion region = new TextureRegion(Res.getTextureByName(name));
            textureArray.add(region);
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>( frameDuration, textureArray );
        anim.setPlayMode(playMode);
        setAnimation(anim);
    }

    protected void createAnimationFromSheet(float frameDuration, String name, int rows, int cols) {
        createAnimationFromSheet(frameDuration, Animation.PlayMode.NORMAL, name, rows, cols);
    }

    protected void createAnimationFromSheet(float frameDuration, Animation.PlayMode playMode, String name, int rows, int cols) {

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



    //-----------------------------------
    //----------OTHER METHODS------------
    //-----------------------------------
    protected TextureRegion currentKeyFrame() {
        return animation.getKeyFrame(elapseTime);
    }

    protected void setAnimationPause(boolean pause) {
        animationPause = pause;
    }

    protected boolean isAnimationPause() {
        return animationPause;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!animationPause)
        elapseTime += delta;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Color tint = getColor();
        batch.setColor(tint);

        if (isVisible()) {
            draw(batch, currentKeyFrame());
        }
    }
}

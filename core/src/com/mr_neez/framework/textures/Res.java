package com.mr_neez.framework.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Res {

    private static Map<String, Object> r;

    public static void initialize() {
        r = new HashMap<>();
    }

    public static Texture loadTexture(String name) {
        Texture texture =  new Texture( Gdx.files.internal(name) );
        r.put(name, texture);
        return texture;
    }

    public static Texture getTextureByName(String name) {
        Object obj = r.get(name);

        // check if the obj is an Texture
        if ( obj instanceof Texture ) {
            return (Texture) obj;
        } else {
            throw new RuntimeException(
                    "invalid name passed to " + Res.class.getName() + ".getTextureByName()"
            );
        }
    }
}

package com.mr_neez.framework.entity.plugins;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mr_neez.framework.entity.Entity;




/**
 * To use this class object it is required to implement CollisionPlugin.Collision Interface
 * by implementing the interface you can call the collision plugin methods
 */
public class CollisionPlugin {

    public interface Collision {
        public void overlap(Collision other);
        public void preventOverlap();
    }

    private Polygon polygon;
    private final Entity entity;

    public CollisionPlugin(Entity entity) {
        this.entity = entity;
    }

    public void createObjectShape(int n) {
        float s = 6.25f / n;

        float[] points = new float[2 * n];
        for (int i = 0; i < n; i++) {
            float x = entity.getWidth() / 2 + entity.getWidth() / 2 * MathUtils.cos(s * i);
            float y = entity.getHeight() / 2 + entity.getHeight() / 2 * MathUtils.sin(s * i);

            points[i * 2] = x;
            points[i * 2 + 1] = y;
        }

        polygon = new Polygon(points);
    }

    public Polygon getShape() {
        // sync polygon with entity
        polygon.setPosition(entity.getX(), entity.getY());
        polygon.setOrigin(entity.getOriginX(), entity.getOriginY());
        polygon.setRotation(entity.getRotation());
        polygon.setScale(entity.getScaleX(), entity.getScaleY());
        return polygon;
    }


    public boolean overlap(CollisionPlugin other) {
        Polygon poly1 = this.getShape();
        Polygon poly2 = other.getShape();

        // preliminary check to improve performance
        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle())) {
            return false;
        }
        return Intersector.overlapConvexPolygons(poly1, poly2);
    }

    public Vector2 preventOverlap(CollisionPlugin other) {
        Polygon poly1 = this.getShape();
        Polygon poly2 = other.getShape();

        // preliminary check to improve performance
        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle())) {
            return null;
        }

        Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector();
        if (Intersector.overlapConvexPolygons(poly1, poly2, mtv)) {
            entity.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
        }
        return mtv.normal;
    }

}

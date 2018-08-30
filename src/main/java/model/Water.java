package model;

import model.gameobjects.DynamicObject;
import model.gameobjects.ForceApplying;
import model.gameobjects.StaticEnvironmentObject;
import processing.core.PImage;
import processing.core.PVector;

public class Water extends StaticEnvironmentObject implements ForceApplying {

    private static final float WATER_UPTHRUST_FORCE = .3f;

    public Water(PVector position, PImage img) {
        super(position, img);
    }

    @Override
    public void applyForce(DynamicObject object) {
        if (object.getImageHitbox().isCollision(this.hitbox)) {
            object.getVelocity().y -= Math.max((object.getPosition().y - this.position.y), 0) * WATER_UPTHRUST_FORCE;
        }
    }
}

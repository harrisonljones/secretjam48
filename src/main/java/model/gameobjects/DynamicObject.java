package model.gameobjects;

import lombok.Getter;
import lombok.Setter;
import processing.core.PImage;
import processing.core.PVector;

@Getter
@Setter
public class DynamicObject extends AbstractDrawable {

    private static final PVector GRAVITY_FORCE = new PVector(0, .8f);

    protected PVector velocity;
    protected boolean isAffectedByGravity;

    public DynamicObject(PVector position, PImage img) {
        super(position, img);
        this.velocity = new PVector(0,0);
    }

    public boolean isMovingUp() {
        return velocity.y < 0;
    }

    public boolean isMovingDown() {
        return velocity.y > 0;
    }

    public boolean isMovingRight() {
        return velocity.x > 0;
    }

    public boolean isMovingLeft() {
        return velocity.x < 0;
    }

    public void updateVelocity() {
        this.setVelocity(getNextVel());
    }

    protected PVector getNextVel() {
        return PVector.add(velocity, GRAVITY_FORCE);
    }

    protected void seekObject(GameObject target) {
        if (target.position.x < this.position.x) {
            this.velocity.x = -3;
        }
        else if (target.position.x > this.position.x) {
            this.velocity.x = 3;
        }
    }

    public void updatePosition() {
        position.add(velocity);
    }

}

package model.gameobjects;

import commons.CommonsUtil;
import lombok.Getter;
import lombok.Setter;
import processing.core.PImage;
import processing.core.PVector;

@Getter
@Setter
public class DynamicObject extends GameObject {

    private static final PVector GRAVITY_FORCE = new PVector(0, .8f);

    protected PVector velocity;
    protected boolean affectedByGravity;

    public DynamicObject(PVector position, PImage img) {
        super(position, img);
        this.velocity = new PVector(0,0);
        this.affectedByGravity = true;
    }

    public boolean isMovingUp() {
        return velocity.y < 0;
    }

    public boolean isMovingRight() {
        return velocity.x > 0;
    }

    public void updateVelocity() {
        this.setVelocity(getNextVel());
    }

    protected PVector getNextVel() {
        return PVector.add(velocity, affectedByGravity ? GRAVITY_FORCE : CommonsUtil.PVEC_ZERO);
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

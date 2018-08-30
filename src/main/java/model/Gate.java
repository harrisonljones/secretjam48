package model;

import model.gameobjects.DynamicEnvironmentObject;
import model.gameobjects.Moveable;
import processing.core.PImage;
import processing.core.PVector;

public class Gate extends DynamicEnvironmentObject implements Moveable {

    private PVector base;
    private PVector target;
    private final float easing = 0.75f;

    public Gate(PVector position, PImage img, PVector target) {
        super(position, img);
        this.base = position;
        this.target = target;
    }

    @Override
    public void moveForwards() {
        PVector vecToTarget = PVector.sub(target, position);
        if (vecToTarget.mag() < 1f) {
            position = target;
        }
        else {
            vecToTarget.mult(easing);
            position.add(vecToTarget);
        }
    }

    @Override
    public void moveBack() {
        PVector vecFromTarget = PVector.sub(position, base);
        if (vecFromTarget.mag() < 1f) {
            position = target;
        }
        else {
            vecFromTarget.mult(easing);
            position.add(vecFromTarget);
        }
    }

    @Override
    public void update() {

    }
}

package model;

import model.gameobjects.DynamicObject;
import model.gameobjects.Interactable;
import processing.core.PImage;
import processing.core.PVector;

public class Boat extends DynamicObject implements Interactable {

    public Boat(PVector position, PImage img, boolean affectedByGravity) {
        super(position, img, affectedByGravity);
    }

    @Override
    public void interact() {

    }
}

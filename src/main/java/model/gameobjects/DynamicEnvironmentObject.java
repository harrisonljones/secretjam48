package model.gameobjects;

import processing.core.PImage;
import processing.core.PVector;

public abstract class DynamicEnvironmentObject extends GameObject {

    public DynamicEnvironmentObject(PVector position, PImage img) {
        super(position, img);
    }

}

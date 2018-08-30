package model.gameobjects;

import processing.core.PImage;
import processing.core.PVector;

public class BuoyantObject extends DynamicObject {

    public BuoyantObject(PVector position, PImage img) {
        super(position, img, true);
    }


}

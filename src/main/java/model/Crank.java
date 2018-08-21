package model;

import commons.CommonsUtil;
import model.gameobjects.Interactable;
import model.gameobjects.Moveable;
import model.gameobjects.StaticEnvironmentObject;
import processing.core.PImage;
import processing.core.PVector;

public class Crank extends StaticEnvironmentObject implements Interactable {

    private static final float ROTATE_DEGREE = 20f;
    private Moveable moveable;

    public Crank(PVector position, PImage img, Moveable moveable) {
        super(position, img);
        this.moveable = moveable;
    }

    @Override
    public void interact() {
        CommonsUtil.rotate(this.img, ROTATE_DEGREE);
        moveable.moveForwards();
    }

}

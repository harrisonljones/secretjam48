package model;

import commons.CommonsUtil;
import model.gameobjects.Interactable;
import model.gameobjects.StaticEnvironmentObject;
import processing.core.PImage;
import processing.core.PVector;

public class GateCrank extends StaticEnvironmentObject implements Interactable {

    private static final float ROTATE_DEGREE = 20f;

    public GateCrank(PVector position, PImage img) {
        super(position, img);
    }

    @Override
    public void interact() {
        CommonsUtil.rotate(this.img, ROTATE_DEGREE);
    }

}

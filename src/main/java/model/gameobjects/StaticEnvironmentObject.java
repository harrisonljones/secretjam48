package model.gameobjects;

import lombok.Getter;
import lombok.Setter;
import model.gameobjects.AbstractDrawable;
import model.shapes.Rect;
import processing.core.PImage;
import processing.core.PVector;

@Getter
@Setter
public class StaticEnvironmentObject extends AbstractDrawable {

    private Rect hitbox;

    public StaticEnvironmentObject(PVector position, PImage img) {
        super(position, img);
        hitbox = new Rect(position.x, position.y, img.width, img.height);
    }

}

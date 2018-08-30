package model.gameobjects;

import lombok.Getter;
import lombok.Setter;
import model.shapes.Rect;
import processing.core.PImage;
import processing.core.PVector;

@Getter
@Setter
public class StaticEnvironmentObject extends GameObject {

    protected Rect hitbox;

    public StaticEnvironmentObject(PVector position, PImage img) {
        super(position, img);
        hitbox = new Rect(position.x, position.y, img.width, img.height);
    }

    @Override
    public Rect getImageHitbox() {
        return hitbox; // no need to create a new one as image never moves
    }
}

package model.gameobjects;

import lombok.Getter;
import lombok.Setter;
import model.shapes.Rect;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

@Getter
@Setter
public abstract class AbstractDrawable extends GameObject {

    protected PImage img;

    AbstractDrawable(PVector position, PImage img) {
        super(position);
        this.img = img;
    }

    public void draw(PApplet sketch) {
        sketch.image(img, this.position.x, this.position.y);
    }

    public Rect getHitbox() {
        return new Rect(position.x, position.y, img.width, img.height);
    }

}

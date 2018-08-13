package model.shapes;

import lombok.Getter;
import processing.core.PVector;

@Getter
public class Rect {

    private final float topLeftX;
    private final float topLeftY;
    private final float width;
    private final float height;

    private final float rightX;
    private final float bottomY;

    public Rect(float topLeftX, float topLeftY, float width, float height) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
        this.height = height;

        rightX = topLeftX + width;
        bottomY = topLeftY + height;
    }

    public boolean isCollision(Rect other) {
        return topLeftX < other.getTopLeftX() + other.getWidth() &&
                topLeftX + width > other.getTopLeftX() &&
                topLeftY < other.getTopLeftY() + other.getHeight() &&
                height + topLeftY > other.getTopLeftY();
    }

    public Rect getAdd(PVector translation) {
        return new Rect(topLeftX + translation.x, topLeftY + translation.y, width, height);
    }

    public Rect getAddX(float deltaX) {
        return new Rect(topLeftX + deltaX, topLeftY, width, height);
    }

    public Rect getAddY(float deltaY) {
        return new Rect(topLeftX, topLeftY + deltaY, width, height);
    }

}

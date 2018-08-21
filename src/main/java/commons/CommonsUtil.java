package commons;

import processing.core.PImage;
import processing.core.PVector;

public final class CommonsUtil {

    private CommonsUtil() {}

    public static final PVector PVEC_ZERO = new PVector(0, 0);

    public static void rotate(PImage image, float degrees) {}

    public float getDist(PVector p1, PVector p2) {

        float xDist = p2.x - p1.x;
        float yDist = p2.y - p1.y;

        return (float)Math.sqrt((double)(xDist * xDist + yDist * yDist));
    }

}

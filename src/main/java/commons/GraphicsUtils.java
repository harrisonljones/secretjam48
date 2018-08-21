package commons;

import processing.core.PApplet;

public final class GraphicsUtils {

    private GraphicsUtils() {}

    public static PApplet sketch;

    public void setSketch(PApplet sketch) {
        GraphicsUtils.sketch = sketch;
    }

}

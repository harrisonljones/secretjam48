package model.shapes;

import java.awt.Point;

import javafx.geometry.Point2D;
import processing.core.PVector;

public class StaticRect extends Rect{

    private Point2D[] points;

    public StaticRect(int topLeftX, int topLeftY, int width, int height) {
        super(topLeftX, topLeftY, width, height);
    }

}

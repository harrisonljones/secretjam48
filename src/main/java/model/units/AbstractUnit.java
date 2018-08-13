package model.units;

import lombok.Getter;
import lombok.Setter;
import model.gameobjects.DynamicObject;
import processing.core.PImage;
import processing.core.PVector;

@Getter
@Setter
abstract class AbstractUnit extends DynamicObject {

    private int maxHealth;
    private int health;
    private int strength;
    private int defence;

    AbstractUnit(PVector position, PImage img, int maxHealth, int strength, int defence) {
        super(position, img);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.strength = strength;
        this.defence = defence;
    }

    public abstract void updateImage();

}

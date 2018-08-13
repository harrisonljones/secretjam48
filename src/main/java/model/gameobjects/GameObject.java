package model.gameobjects;

import lombok.Getter;
import lombok.Setter;
import processing.core.PVector;

@Getter
@Setter
public abstract class GameObject {

    protected PVector position;

    GameObject(PVector position) {
        this.position = position;
    }

}

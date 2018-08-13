package model.units;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import processing.core.PImage;
import processing.core.PVector;

@Getter
@Setter
public class EnemyUnit extends AbstractUnit {

    private int imageIndex;
    private List<PImage> leftImages;
    private List<PImage> rightImages;

    private PlayerUnit playerUnit; // For tracking

    public EnemyUnit(PVector position,
                     int maxHealth,
                     int strength,
                     int defence,
                     int imageIndex,
                     List<PImage> leftImages,
                     List<PImage> rightImages,
                     PlayerUnit playerUnit) {
        super(position, leftImages.get(0), maxHealth, strength, defence);

        this.imageIndex = imageIndex;
        this.leftImages = leftImages;
        this.rightImages = rightImages;
        this.playerUnit = playerUnit;
    }

    public void cycleImage() {
        imageIndex = (imageIndex + 1) % leftImages.size();
    }

    public void updateImage() {
        if (velocity.x > 0) {
            img = rightImages.get(imageIndex);
        }
        else {
            img = leftImages.get(imageIndex);
        }
    }

    @Override
    protected PVector getNextVel() {

        if (!this.getHitbox().isCollision(playerUnit.getHitbox())) {
            seekObject(playerUnit);
        }
        return super.getNextVel();
    }

}

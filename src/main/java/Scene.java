import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.gameobjects.ForceApplying;
import model.units.EnemyUnit;
import model.units.PlayerUnit;
import model.gameobjects.DynamicObject;
import model.gameobjects.StaticEnvironmentObject;
import model.shapes.Rect;
import processing.core.PApplet;

@Getter
@Setter
@AllArgsConstructor
class Scene {

    private PlayerUnit player;
    private List<StaticEnvironmentObject> interactiveEnvironmentObjects;
    private List<StaticEnvironmentObject> decorativeEnvironmentObjects;
    private List<EnemyUnit> enemyUnits;
    private List<ForceApplying> forceAppliers;
    private PApplet sketch;

    void update() {

        player.updateVelocity();
        player.updateImage();

        Rect nextRectX = player.getImageHitbox().getAddX(player.getVelocity().x);
        Rect nextRectY = player.getImageHitbox().getAddY(player.getVelocity().y);

        for (StaticEnvironmentObject object : interactiveEnvironmentObjects) {
            if (object.getImageHitbox().isCollision(nextRectX)) {
                allignWithObject(player, object.getHitbox(), true);
                player.getVelocity().x = 0;
                break;
            }
        }

        for (StaticEnvironmentObject object : interactiveEnvironmentObjects) {
            if (object.getImageHitbox().isCollision(nextRectY)) {
                allignWithObject(player, object.getHitbox(), false);
                player.land();
                break;
            }
        }

        player.updatePosition();

        for (EnemyUnit unit : enemyUnits) {
            unit.updateVelocity();
            nextRectX = unit.getImageHitbox().getAddX(unit.getVelocity().x);
            nextRectY = unit.getImageHitbox().getAddY(unit.getVelocity().y);

            for (StaticEnvironmentObject object : interactiveEnvironmentObjects) {
                if (object.getImageHitbox().isCollision(nextRectX)) {
                    allignWithObject(unit, object.getImageHitbox(), true);
                    unit.getVelocity().x = 0;
                    break;
                }
            }

            for (StaticEnvironmentObject object : interactiveEnvironmentObjects) {
                if (object.getImageHitbox().isCollision(nextRectY)) {
                    allignWithObject(unit, object.getImageHitbox(), false);
                    unit.getVelocity().y = 0;
                    break;
                }
            }

            unit.updatePosition();
            unit.updateImage();
        }

        for (ForceApplying forceApplying : forceAppliers) {
            forceApplying.applyForce(player);
        }

    }

    private static void allignWithObject(DynamicObject unit, Rect hitbox, boolean allignX) {

        if (allignX) {
            if (unit.isMovingRight()) {
                unit.getPosition().x = hitbox.getTopLeftX() - (unit.getImageHitbox().getWidth());
            }
            else {
                unit.getPosition().x = hitbox.getRightX();
            }
        }
        else {
            if (unit.isMovingUp()) {
                unit.getPosition().y = hitbox.getBottomY();
            }
            else {
                unit.getPosition().y = hitbox.getTopLeftY() - (unit.getImageHitbox().getHeight());
            }
        }

    }

    void draw() {
        decorativeEnvironmentObjects.forEach(object -> object.draw(sketch));
        interactiveEnvironmentObjects.forEach(object -> object.draw(sketch));
        enemyUnits.forEach(unit -> unit.draw(sketch));
        player.draw(sketch);
    }

}

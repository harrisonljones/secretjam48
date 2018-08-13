import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.units.EnemyUnit;
import model.units.PlayerUnit;
import model.gameobjects.DynamicObject;
import model.gameobjects.StaticEnvironmentObject;
import model.shapes.Rect;
import processing.core.PApplet;

@Getter
@Setter
@AllArgsConstructor
public class Scene {

    private PlayerUnit player;
    private List<StaticEnvironmentObject> staticEnvironmentObjects;
    private List<EnemyUnit> enemyUnits;
    private PApplet sketch;

    public void update() {

        player.updateVelocity();
        player.updateImage();

        Rect nextRectX = player.getHitbox().getAddX(player.getVelocity().x);
        Rect nextRectY = player.getHitbox().getAddY(player.getVelocity().y);

        for (StaticEnvironmentObject object : staticEnvironmentObjects) {
            if (object.getHitbox().isCollision(nextRectX)) {
                allignWithObject(player, object.getHitbox(), true);
                player.getVelocity().x = 0;
                break;
            }
        }

        for (StaticEnvironmentObject object : staticEnvironmentObjects) {
            if (object.getHitbox().isCollision(nextRectY)) {
                allignWithObject(player, object.getHitbox(), false);
                player.land();
                break;
            }
        }

        player.updatePosition();

        for (EnemyUnit unit : enemyUnits) {
            unit.updateVelocity();
            unit.updateImage();
            nextRectX = unit.getHitbox().getAddX(unit.getVelocity().x);
            nextRectY = unit.getHitbox().getAddY(unit.getVelocity().y);

            for (StaticEnvironmentObject object : staticEnvironmentObjects) {
                if (object.getHitbox().isCollision(nextRectX)) {
                    allignWithObject(unit, object.getHitbox(), true);
                    unit.getVelocity().x = 0;
                    break;
                }
            }

            for (StaticEnvironmentObject object : staticEnvironmentObjects) {
                if (object.getHitbox().isCollision(nextRectY)) {
                    allignWithObject(unit, object.getHitbox(), false);
                    unit.getVelocity().y = 0;
                    break;
                }
            }

            unit.updatePosition();
        }
    }

    private static void allignWithObject(DynamicObject unit, Rect hitbox, boolean allignX) {

        if (allignX) {
            if (unit.isMovingRight()) {
                unit.getPosition().x = hitbox.getTopLeftX() - (unit.getHitbox().getWidth());
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
                unit.getPosition().y = hitbox.getTopLeftY() - (unit.getHitbox().getHeight());
            }
        }

    }

    public void draw() {
        staticEnvironmentObjects.forEach(object -> object.draw(sketch));
        enemyUnits.forEach(unit -> unit.draw(sketch));
        player.draw(sketch);
    }

}

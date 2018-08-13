import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.units.EnemyUnit;
import model.units.PlayerUnit;
import model.gameobjects.StaticEnvironmentObject;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class MainApplication extends PApplet {

    private boolean leftKeyDown;
    private boolean rightKeyDown;

    private Scene scene;
    private PlayerUnit playerUnit;
    private List<EnemyUnit> enemyUnits;
    private List<StaticEnvironmentObject> environmentObjects;

    private static int BG_COL;

    private static final float MOVING_VELOCITY_X = 8f;
    private static final float BACKWARDS_MOVING_VELOCITY_X = -MOVING_VELOCITY_X;

    public void settings() {
        size(1200, 750);
    }

    public void setup() {
        BG_COL = color(255);

        environmentObjects = new ArrayList<>();
        environmentObjects.add(new StaticEnvironmentObject(new PVector(100, 600), loadImage("images/platform.png")));
        environmentObjects.add(new StaticEnvironmentObject(new PVector(556, 552), loadImage("images/platform.png")));
        environmentObjects.add(new StaticEnvironmentObject(new PVector(980, 304), loadImage("images/gate.png")));

        List<PImage> playerImages = Arrays.asList(
                loadImage("images/player_char_left_foot.png"),
                loadImage("images/player_char_right_foot.png"),
                loadImage("images/player_char_both_feet.png"));

        playerUnit = new PlayerUnit(new PVector(150, 400), 100, 100, 100, playerImages);

        List<PImage> enemyLeftImages = Arrays.asList(
                loadImage("images/small_slime_left1.png"),
                loadImage("images/small_slime_left2.png"),
                loadImage("images/small_slime_left3.png"));

        List<PImage> enemyRightImages = Arrays.asList(
                loadImage("images/small_slime_right1.png"),
                loadImage("images/small_slime_right2.png"),
                loadImage("images/small_slime_right3.png"));

        enemyUnits = new ArrayList<>();
        enemyUnits.add(new EnemyUnit(
                new PVector(700, 200), 0, 0, 0, 0, enemyLeftImages, enemyRightImages, playerUnit
        ));

        scene = new Scene(playerUnit, environmentObjects, enemyUnits, this);

        frameRate(60);
    }

    public void draw() {
        background(BG_COL);

        if (rightKeyDown) {
            if (leftKeyDown) {
                playerUnit.getVelocity().x = 0;
            }
            else {
                playerUnit.getVelocity().x = MOVING_VELOCITY_X;
            }
        }
        else if (leftKeyDown) {
            playerUnit.getVelocity().x = BACKWARDS_MOVING_VELOCITY_X;
        }
        else {
            playerUnit.getVelocity().x = 0;
        }

        scene.update();
        scene.draw();
    }

    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == RIGHT) {
                rightKeyDown = true;
            }
            else if (keyCode == LEFT) {
                leftKeyDown = true;
            }
            else if (keyCode == UP) {
                playerUnit.jump();
            }
        }
    }

    public void keyReleased() {
        if (key == CODED) {
            if (keyCode == RIGHT) {
                rightKeyDown = false;
            }
            else if (keyCode == LEFT) {
                leftKeyDown = false;
            }
        }
    }

    public static void main(String... args) {
        PApplet.main("MainApplication");
    }

}
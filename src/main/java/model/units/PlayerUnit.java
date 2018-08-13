package model.units;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import model.fsm.FSMAction;
import model.fsm.FiniteStateMachine;
import model.fsm.TimeFSMAction;
import model.fsm.TimedFiniteStateMachine;
import model.gameobjects.AbstractDrawable;
import processing.core.PImage;
import processing.core.PVector;

@Getter
@Setter
public class PlayerUnit extends AbstractUnit {

    private int imageIndex;
    private List<PImage> playerImages;
    private List<PImage> weaponImages;
    private FiniteStateMachine jumpingStateMachine;
    private TimedFiniteStateMachine attackingStateMachine;

    private static final float JUMP_SPEED_Y = -15f;

    public PlayerUnit(PVector position,
                     int maxHealth,
                     int strength,
                     int defence,
                     List<PImage> playerImages) {
        super(position, playerImages.get(PlayerUnitImage.BOTH_FEET), maxHealth, strength, defence);
        this.imageIndex = PlayerUnitImage.BOTH_FEET;
        this.playerImages = playerImages;
        setupJumpingStateMachine();
    }

    private static class PlayerUnitImage {
        private final static int LEFT_FOOT = 0;
        private final static int RIGHT_FOOT = 1;
        private final static int BOTH_FEET = 2;

    }

    public void updateImage() {
        img = playerImages.get(
                velocity.x > 0 ? PlayerUnitImage.LEFT_FOOT :
                velocity.x < 0 ? PlayerUnitImage.RIGHT_FOOT :
                                 PlayerUnitImage.BOTH_FEET);
    }

    public void jump() {
        if (jumpingStateMachine.getCurrentState() != STATE_DOUBLE_JUMPING) {
            jumpingStateMachine.act(ACTION_JUMP);
            velocity.y = JUMP_SPEED_Y;
        }
    }

    public void land() {
        jumpingStateMachine.act(ACTION_LAND);
        velocity.y = 0;
    }

    public void attack() {
        attackingStateMachine.act(ACTION_ATTACK);

    }

    @Override
    public void updatePosition() {
        position = PVector.add(position, velocity);
    }

    private static final int STATE_NEUTRAL = 0;
    private static final int STATE_JUMPING = 1;
    private static final int STATE_DOUBLE_JUMPING = 2;
    private static final int ACTION_JUMP = 0;
    private static final int ACTION_LAND = 1;

    private void setupJumpingStateMachine() {
        jumpingStateMachine = new FiniteStateMachine();
        jumpingStateMachine.getActions().add(new FSMAction(STATE_NEUTRAL, ACTION_JUMP, STATE_JUMPING));
        jumpingStateMachine.getActions().add(new FSMAction(STATE_JUMPING, ACTION_JUMP, STATE_DOUBLE_JUMPING));
        jumpingStateMachine.getActions().add(new FSMAction(STATE_JUMPING, ACTION_LAND, STATE_NEUTRAL));
        jumpingStateMachine.getActions().add(new FSMAction(STATE_DOUBLE_JUMPING, ACTION_LAND, STATE_NEUTRAL));
    }

    private static final int STATE_ATTACKING = 1;
    private static final int STATE_ATTACKED = 2;
    private static final int ACTION_ATTACK = 1;

    private void setupAttackingStateMachine() {
        attackingStateMachine = new TimedFiniteStateMachine();
        attackingStateMachine.getActions().add(new FSMAction(STATE_NEUTRAL, ACTION_ATTACK, STATE_ATTACKING));
        attackingStateMachine.getTimeActions().add(new TimeFSMAction(STATE_ATTACKING, 1000, STATE_ATTACKED));
        attackingStateMachine.getTimeActions().add(new TimeFSMAction(STATE_ATTACKED, 1000, STATE_NEUTRAL));
    }

}

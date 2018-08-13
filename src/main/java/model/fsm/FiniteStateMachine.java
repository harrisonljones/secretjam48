package model.fsm;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

@Getter
public class FiniteStateMachine {

    protected int currentState; // initial state is 0
    private final Set<FSMAction> actions;

    public FiniteStateMachine() {
        actions = new HashSet<>();
    }

    public FiniteStateMachine(Set<FSMAction> actions) {
        this.actions = actions;
    }

    public void act(int actionIndex) {
        for (FSMAction action : actions) {
            if (action.getState() == currentState && action.getAction() == actionIndex) {
                currentState = action.getNextState();
                break;
            }
        }
    }
}

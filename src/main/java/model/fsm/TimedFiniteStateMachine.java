package model.fsm;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

@Getter
public class TimedFiniteStateMachine extends FiniteStateMachine {

    private long timeEnteredCurrentState;
    private Set<TimeFSMAction> timeActions;

    public TimedFiniteStateMachine() {
        super();
        timeActions = new HashSet<>();
        timeEnteredCurrentState = System.currentTimeMillis();
    }

    public TimedFiniteStateMachine(Set<FSMAction> actions, Set<TimeFSMAction> timeActions) {
        super(actions);
        this.timeActions = timeActions;
        timeEnteredCurrentState = System.currentTimeMillis();
    }

    public void update() {
        long timeSpentInCurrentState = System.currentTimeMillis() - timeEnteredCurrentState;

        for (TimeFSMAction action : timeActions) {
            if (action.getState() == currentState && timeSpentInCurrentState > action.getTime()) {
                currentState = action.getNextState();
                timeEnteredCurrentState = System.currentTimeMillis();
            }
        }
    }

}

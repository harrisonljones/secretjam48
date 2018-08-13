package model.fsm;

import lombok.Value;

@Value
public class TimeFSMAction {

    int state;
    long time; // ms
    int nextState;

}

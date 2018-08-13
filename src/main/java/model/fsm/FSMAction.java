package model.fsm;

import lombok.Value;

@Value
public class FSMAction {

    int state;
    int action;
    int nextState;

}

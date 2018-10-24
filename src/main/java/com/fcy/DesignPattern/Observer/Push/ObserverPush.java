package com.fcy.DesignPattern.Observer.Push;

import com.fcy.DesignPattern.Observer.Event;
import com.fcy.DesignPattern.Observer.State;

public class ObserverPush {
    public void react(Event cmd){
        State Old=(State)cmd.getOld();
        State New=(State)cmd.getNew();
        dis(Old,New);
    }
    public void dis(State Old,State New){
        System.out.println(Old+"-->"+New);
    }
}

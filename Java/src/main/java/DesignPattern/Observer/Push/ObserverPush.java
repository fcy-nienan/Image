package DesignPattern.Observer.Push;

import DesignPattern.Observer.Event;
import DesignPattern.Observer.State;

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

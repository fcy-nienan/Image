package com.fcy.Java.DesignPattern.Observer.Pull;

import com.fcy.Java.DesignPattern.Observer.State;

public class ObserverPull {
    public void react(SubjectPull subjectPullPull){
        State state=subjectPullPull.getState();
        System.out.println(state);
    }
}
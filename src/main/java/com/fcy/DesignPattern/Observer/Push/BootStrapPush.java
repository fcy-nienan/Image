package com.fcy.DesignPattern.Observer.Push;

import com.fcy.DesignPattern.Observer.State;

public class BootStrapPush {
    public static void main(String[] args) {
        SubjectPush subjectPush=new SubjectPush();

        ObserverPush observerPush=new ObserverPush();
        ObserverPush observerPush1=new ObserverPush();
        ObserverPush observerPush2=new ObserverPush();

        subjectPush.addObserver(observerPush);
        subjectPush.addObserver(observerPush1);
        subjectPush.addObserver(observerPush2);

        subjectPush.change(State.BLUE);
    }
}

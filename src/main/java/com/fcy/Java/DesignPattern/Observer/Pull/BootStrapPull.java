package com.fcy.Java.DesignPattern.Observer.Pull;

import com.fcy.Java.DesignPattern.Observer.State;

public class BootStrapPull {
    public static void main(String[] args) {
        SubjectPull subjectPull=new SubjectPull();
        ObserverPull observerPull=new ObserverPull();
        ObserverPull observerPull1=new ObserverPull();
        ObserverPull observerPull2=new ObserverPull();

        subjectPull.add(observerPull);
        subjectPull.add(observerPull1);
        subjectPull.add(observerPull2);

        subjectPull.change(State.ORGANGE);
    }
}

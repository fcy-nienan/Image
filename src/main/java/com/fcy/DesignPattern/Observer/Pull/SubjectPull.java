package com.fcy.DesignPattern.Observer.Pull;


import com.fcy.DesignPattern.Observer.State;

import java.util.ArrayList;

/*
* 可以自己选择是否传递旧的主题状态给观察者
* */
public class SubjectPull {
    private volatile State state= State.RED;
    public State getState() {
        return state;
    }
    private ArrayList<ObserverPull> all=new ArrayList<>();
    public void change(State state){
        this.state=state;
        notifyObservers();
    }
    public void add(ObserverPull observerPullPull){
        all.add(observerPullPull);
    }
    public void remove(ObserverPull observerPullPull){
        if(all.contains(observerPullPull)){
            all.remove(observerPullPull);
        }
    }
    public void notifyObservers(){
        all.forEach(n->{
            n.react(this);
        });
    }
}

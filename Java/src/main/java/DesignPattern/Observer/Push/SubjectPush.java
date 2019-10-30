package DesignPattern.Observer.Push;

import DesignPattern.Observer.Event;
import DesignPattern.Observer.State;

import java.util.ArrayList;
import java.util.List;

public class SubjectPush {
    private volatile State state= State.RED;
    private List<ObserverPush> all=new ArrayList<>();
    public void change(State New){
        state=New;
        Event event=new Event(State.RED,State.BLACK);
        notifyObservers(event);
    }
    public void addObserver(ObserverPush ob){
        all.add(ob);
    }
    public void removeObserver(ObserverPush ob){
//        object对象的equals方法比较的是引用
//        ArrayList的contains方法默认使用该对象的equals方法来确定是否存在
        if(all.contains(ob)){
            all.remove(ob);
        }
    }
    public void notifyObservers(Event event){
        all.forEach(n->{
            n.react(event);
        });
    }
}

package com.fcy.Java.DesignPattern1.EventDriver;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-25  17:49
 */
public class Main {
    public static void main(String[] args) {
        EventManager manager=new EventManager();
        System.out.println(manager.hashCode());
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        while(true){
            Event event=manager.getNext();
            if (event!=null){
                dispatch(event);
            }
        }

    }
    public static void dispatch(Event event){

    }
}

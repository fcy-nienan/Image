package com.fcy.Java.DesignPattern.Visit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  13:18
 */
public class Main {
    public static void main(String[] args) {
        Computer computer=new Computer();
        computer.add(new MousePart());
        computer.add(new KeyboardPart());
        computer.add(new MonitorPart());
        ComputerVisit visit=new DetailVisit();
        computer.accept(visit);
    }
}

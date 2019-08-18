package com.fcy.Java.DesignPattern.Visit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  11:43
 */
public class DetailVisit implements ComputerVisit {
    public void visit(MonitorPart part) {
        System.out.println(part.getName());
    }

    @Override
    public void visit(KeyboardPart part) {
        System.out.println(part.getName());
    }

    @Override
    public void visit(MousePart part) {
        System.out.println(part.getName());
    }

    @Override
    public void visit(Computer part) {
        System.out.println(part.getName());
    }
}

package DesignPattern.Visit;

import lombok.Getter;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  11:40
 */
public class MonitorPart implements ComputerPart {
    @Getter
    private String name="Monitor";
    @Override
    public void accept(ComputerVisit visit) {
        visit.visit(this);
    }
}

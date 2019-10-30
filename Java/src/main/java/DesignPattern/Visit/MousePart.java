package DesignPattern.Visit;


import lombok.Getter;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  11:38
 */
public class MousePart implements ComputerPart {
    @Getter
    private String name="Mouse";
    @Override
    public void accept(ComputerVisit visit) {
        visit.visit(this);
    }
}

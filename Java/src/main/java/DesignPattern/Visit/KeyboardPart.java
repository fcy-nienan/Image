package DesignPattern.Visit;

import lombok.Getter;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  11:39
 */
public class KeyboardPart implements ComputerPart {
    @Getter
    private String name="Keyboard";
    @Override
    public void accept(ComputerVisit visit) {
        visit.visit(this);
    }
}

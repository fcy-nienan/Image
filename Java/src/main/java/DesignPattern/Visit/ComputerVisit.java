package DesignPattern.Visit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  11:37
 */
public interface ComputerVisit {
    void visit(MonitorPart part);
    void visit(KeyboardPart part);
    void visit(MousePart part);
    void visit(Computer part);
}

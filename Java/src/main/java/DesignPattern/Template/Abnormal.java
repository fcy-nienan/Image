package DesignPattern.Template;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:42
 */
public class Abnormal extends AbstractPerson {

    @Override
    public void play() {
        System.out.println("Abnormal play");
    }

    @Override
    public void married() {
        System.out.println("No Wife");
    }

    @Override
    public void death() {
        System.out.println("Abnormal Death");
    }
}

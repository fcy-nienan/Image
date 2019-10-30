package DesignPattern.Template;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:41
 */
public class NormalPerson extends AbstractPerson {

    @Override
    public void play() {
        System.out.println("I am normal play");
    }

    @Override
    public void married() {
        System.out.println("A Normal Wife");
    }

    @Override
    public void death() {
        System.out.println("Normal Death");
    }
}

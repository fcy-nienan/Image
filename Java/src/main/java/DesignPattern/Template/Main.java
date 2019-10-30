package DesignPattern.Template;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:43
 */
public class Main {
    public static void main(String[] args) {
        AbstractPerson normalPerson=new NormalPerson();
        normalPerson.life();
        AbstractPerson abnormal=new Abnormal();
        abnormal.life();
    }
}

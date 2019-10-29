package DesignPattern.Template;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:39
 */
public abstract class AbstractPerson {
    public void life(){
        birth();
        grow();
        play();
        married();
        death();
    }
    private void birth(){
        System.out.println("Hello World,I Com!");
    }
    private void grow(){
        System.out.println("Long Long Time");
    }
    public abstract void play();
    public abstract void married();
    public abstract void death();

}

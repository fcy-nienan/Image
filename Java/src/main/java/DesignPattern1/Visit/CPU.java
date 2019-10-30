package DesignPattern1.Visit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-25  16:23
 */
public class CPU extends AbstractAcceptor {
    public int addInt(int a,int b){
        return a+b;
    }
    public int dcrInt(int a,int b){
        return a-b;
    }
    public int mulInt(int a,int b){
        return a*b;
    }
    public int divInt(int a,int b){
        if(b==0) throw new IllegalArgumentException("divide can not be zero!");
        return a/b;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}

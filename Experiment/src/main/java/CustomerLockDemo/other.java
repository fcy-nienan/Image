package Concurrent.CustomerLockDemo;

public class other {
    public static void main(String args[]) {
        jniDemo demo=new jniDemo();
        long start,end;
        start=System.currentTimeMillis();
        for (int i=0;i<Integer.MAX_VALUE;i++){
            demo.increment(i);
        }
        end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));

        start=System.currentTimeMillis();
        for (int i=0;i<Integer.MAX_VALUE;i++){
            demo.incrementX(i);
        }
        end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));

    }
}

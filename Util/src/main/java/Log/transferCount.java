package Log;

import java.util.concurrent.atomic.AtomicInteger;

public class transferCount {
    private static AtomicInteger count=new AtomicInteger();
    public static int getCount(){
        return count.getAndIncrement();
    }
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            System.out.println(getCount());
        }
    }

}

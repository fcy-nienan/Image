package Log;

import java.util.concurrent.atomic.AtomicInteger;

public class transferCount {
    private static AtomicInteger count=new AtomicInteger();
    public static int getCount(){
        return count.getAndIncrement();
    }
}

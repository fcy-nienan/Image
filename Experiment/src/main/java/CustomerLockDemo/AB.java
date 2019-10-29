package Concurrent.CustomerLockDemo;
public class AB {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        for (int i = 0; i < 111111111; i++) {
            Thread.enumerate(new Thread[]{Thread.currentThread()});
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1 + "ms");

        long t3 = System.currentTimeMillis();
        for (int i = 0; i < 111111111; i++) {
            enumerate(new Thread[]{Thread.currentThread()});
        }
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3 + "ms");

    }

    public static int enumerate(Thread tarray[]) {
        return Thread.currentThread().getThreadGroup().enumerate(tarray);
    }
}


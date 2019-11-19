package ThreadUtil;

public class TMS {
    public static void sleep(long mill){
        try {
            Thread.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package ThreadUtil;

public class TMS {
    public static void sleep(long mill){
        try {
            Thread.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    sleep(1000);
                }
            }
        });
        t.start();
        while (true){
            System.out.println(t.isInterrupted());
            t.interrupt();
            System.out.println(t.isInterrupted());
            sleep(1000);
        }
    }
}

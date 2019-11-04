package Advanced.Concurrent.Thread;

import java.util.TreeMap;

public class InterruptDemo {
    public static void main(String[] args)throws Exception {
        testT1();
    }
    public static void testT(){
        T t=new T();
        t.start();
        System.out.println("Main:before:"+t.isInterrupted());
        t.interrupt();
        System.out.println("Main:after:"+t.isInterrupted());
    }
    public static void testT1() throws InterruptedException {
//        T1 t1=new T1();
//        t1.start();
//        Thread.sleep(2000);
//        System.out.println("Main:"+t1.isInterrupted());
//        t1.interrupt();
//        System.out.println("Main:"+t1.isInterrupted());
//        t1.interrupt();
//        System.out.println("Main:"+t1.isInterrupted());
//
//        Thread.interrupted();
//        System.out.println();
//        Thread.interrupted();
        System.out.println(Thread.currentThread().isInterrupted());//false
        Thread.currentThread().interrupt();//true
//        System.out.println(Thread.interrupted());
        System.out.println(Thread.currentThread().isInterrupted());//true
        System.out.println(Thread.interrupted());//true
        System.out.println(Thread.currentThread().isInterrupted());//false
        System.out.println(Thread.interrupted());//false
        System.out.println(Thread.currentThread().isInterrupted());//false
    }
    static class T extends Thread{
        @Override
        public void run() {
            try{
                Thread.sleep(3000);
                synchronized (this) {
                    this.wait();
                }
            }catch (InterruptedException e){
                System.out.println("中断");
                System.out.println(this.isInterrupted());
                System.out.println(this.isInterrupted());
                e.printStackTrace();

            }

        }
    }
    static class T1 extends Thread{
        @Override
        public void run() {
            while(!this.isInterrupted()){
                for(int i=0;i<10000;i++){
                    for(int j=0;j<10000;j++){
                        for(int k=0;k<10000;k++){
                            int mm=i+k+j;
                        }
                    }
                }
                System.out.println("我在运行");
            }
            System.out.println(this.isInterrupted());
            System.out.println("运行结束");
        }
    }
    static class note{
        static Thread t=new Thread();
        TreeMap map;
        public static void main(String[] args) {
            //中断状态默认为false，清除中断状态意味着 如果中断状态为true，则改为false
//                                         如果中断状态为false 则改为false
            Thread.interrupted();//返回中断状态并清除中断状态
            t.isInterrupted();//返回中断状态
//        以上两个方法都调用了private native boolean isInterrupted(boolean ClearInterrupted);
//        本地方法，参数标识是否清除中断状态
            t.interrupt();//置中断状态为true
        }
    }
}


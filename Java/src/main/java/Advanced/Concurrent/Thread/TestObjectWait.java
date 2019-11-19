package Advanced.Concurrent.Thread;

//测试锁池和等待池
//
/*
线程A获取锁,线程B进入BLOCKING状态,线程C进入BLOCKING状态,线程A调用wait方法进入WAITING状态
线程B,C竞争锁,竞争到了的锁通知等待池中的对象
查看各个线程的状态

* */
public class TestObjectWait {
    private Object obj=new Object();
    class threadA extends Thread{
        @Override
        public void run() {
            synchronized (obj){
                try {
                    Thread.sleep(3000);
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("a线程:运行");
                }
            }
        }
    }
    class threadB extends Thread{
        private Thread a;
        private Thread b;
        public threadB(Thread b,Thread c){
            super();
            this.a=b;
            this.b=c;
        }
        @Override
        public void run() {
            synchronized (obj){
                System.out.println("b线程:通知前a状态:"+a.getState()
                +"c状态:"+b.getState());
                obj.notifyAll();
                try {
                    System.out.println("b线程:通知后a状态:"+a.getState()+"c状态:"+b.getState());
                    Thread.sleep(2000);
                    System.out.println("b线程:通知二秒后a状态:"+a.getState()+"c状态:"+b.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("b线程:运行完毕");
                }
            }
        }
    }
    class threadC extends Thread{
        private Thread a;
        public threadC(Thread a){
            super();
            this.a=a;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("c线程开始竞争锁");
            synchronized (obj){
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("c线程:运行"+"  a线程状态:"+a.getState());
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestObjectWait wait=new TestObjectWait();
        threadA a=wait.new threadA();
        threadC c=wait.new threadC(a);
        threadB b=wait.new threadB(a,c);

//        a.setPriority(5);
//        c.setPriority(10);

        a.start();
        System.out.println("a线程:"+a.getState());
        Thread.sleep(1000);
        b.start();
        c.start();
        Thread.sleep(1000);
        while(true) {
            Thread.sleep(1000);
            System.out.println("主线程:b线程:" + b.getState());
            System.out.println("主线程:c线程:" + c.getState());
            System.out.println("主线程:a线程:"+a.getState());

        }
    }
}

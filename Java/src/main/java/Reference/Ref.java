package Reference;

import lombok.Data;
import lombok.ToString;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * @descripiton: java的四种引用
 * 强引用  即使发生内存溢出也不回收，除非GcRoot不到
 * 软引用  一般情况下不回收，如果内存不够才回收
 * 弱引用  发生Gc则回收
 * 虚引用  可有可无的
 * @author: fcy
 * @date: 2018-08-25  13:45
 */
@Data
@ToString
public class Ref {
    private String username="fcy";
    private String password="fcy";
    private byte[] bytes=new byte[1024*1024*5];
    @Override
    protected void finalize()throws Throwable{
        System.out.println("survivor");
    }
    public static void main(String args[]) throws InterruptedException {
        BlockingQueue qeue;
        ArrayList list;

        Object object=new Object();
        ReferenceQueue queue=new ReferenceQueue();
        Map<Object,Object> map=new HashMap<>();

        map.entrySet().iterator();
        Thread t2=new Thread(()->{
            while(true){
                for(int i=0;i<5;i++) {
                    Ref ref1=new Ref();
                    WeakReference<Ref> softReference = new WeakReference<>(ref1, queue);
                    map.put(softReference, object);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(map.size());

            }
        });
        t2.start();
        Thread t=new Thread(()->{
            try{
                int ctn=0;
                WeakReference<Ref> weakReference=null;
                while((weakReference= (WeakReference<Ref>) queue.remove())!=null){
                    ctn++;
                    System.out.println(ctn+"回收了:"+weakReference);
                }
            }catch (Exception e){

            }
        });
        t.start();

    }
}

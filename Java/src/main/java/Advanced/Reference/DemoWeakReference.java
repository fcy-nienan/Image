package Advanced.Reference;


import lombok.extern.slf4j.Slf4j;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-06  14:07
 *
 * 四种引用
 *
 * 强引用
 * 软引用
 * 若引用
 * 虚引用
 *
 *
 *
 */
@Slf4j
public class DemoWeakReference {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue queue=new ReferenceQueue();
        ReferencedObject object=new ReferencedObject();
        WeakReference reference=new WeakReference(object,queue);
        checkReferenceQueueThread thread=new checkReferenceQueueThread(queue);
        thread.start();
//        int i=0;
//        long start=System.currentTimeMillis();
//        while (true){
//            if (reference.get()!=null){
//                i++;
//                if (i==119000){
//                    Thread.sleep(1000*6);
//                }
//                log.info(String.valueOf(queue.poll()==null));
//                log.info("object has in memory and live {} loop!",i);
//            }else{
//                log.info("reference queue 's object is {}",queue.poll().get());
//                log.info("object has been collected!");
//                break;
//            }
//        }
        Thread.sleep(100000000);

    }

}

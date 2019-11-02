package Reference;


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
public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue queue=new ReferenceQueue();
        Student student=new Student("fcy",11.2f);
        WeakReference<Student> reference=new WeakReference<Student>(student);
        int i=0;
        long start=System.currentTimeMillis();
        while (true){
            if (reference.get()!=null){
                i++;
                if (i==119000){
                    Thread.sleep(1000*60*60*100);
                }
                System.out.println("object has in memory and live "+i+" loop!");
            }else{
                System.out.println("object has been collected!");
                break;
            }
        }
        Thread.sleep(100000000);

    }

}

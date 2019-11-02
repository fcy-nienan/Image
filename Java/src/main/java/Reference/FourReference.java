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
public class FourReference {
    public static void main(String[] args) throws InterruptedException {
        Student student=new Student("fcy",11.2f);
        ReferenceQueue queue=new ReferenceQueue();
        WeakReference<Student> reference=new WeakReference<Student>(student);
        int i=0;
        while (true){
            if (reference.get()!=null){
                i++;
                if (i==119000){
                    Thread.sleep(1000*60*60);
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

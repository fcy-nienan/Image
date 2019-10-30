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
        Object o=new Object();
        ReferenceQueue queue=new ReferenceQueue();
        WeakReference reference=new WeakReference(o,queue);
        o=null;
        System.gc();
        Thread.sleep(4000);
        System.out.println(o);
        System.out.println(reference.get());

    }

}

package Reference;

import ThreadUtil.OOMTestThread;
import ThreadUtil.TMS;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
/*
* 幽灵引用的get方法总是返回null是因为源码中这个方法是直接返回null的
* 当垃圾回收器准备回收一个对象时，
* 如果发现它还有虚引用，就会在垃圾回收后，
* 将这个虚引用加入引用队列，在其关联的虚引用出队前，
* 不会彻底销毁该对象。 所以可以通过检查引用队列中是否有相应的虚引用来判断对象是否已经被回收了。
*
* 下面显示的结果是幽灵引用本身并没有被回收,只是回收了其包装的对象
*
* 另外如果要使得该虚引用入队列,必须先将其包含的对象置为null
* 如果不置为null,下面的结果是显示该对象永远也不会被回收
*
* ReferenceQueue的相关方法
* Reference poll()返回进入队列的引用
* Reference remove()等待进入队列的元素并移除，该方法会阻塞知道元素入队列
* Reference remove(long time)加了超时时间
* Reference的方法
* Object get()方法体是直接返回null
* boolean enqueued() 将本引用入队列
* boolean isEnqueued()返回与之关联的队列是否是空队列，如果没有与之关联的队列则一直返回false
* void clear()调用该方法不会造成该对象入队列
* */
@Slf4j
public class DemoPhantomReference {
    private static final List<Object> list=new ArrayList<>();
    public static void main (String args[]) throws InterruptedException {
        execute();
    }
    public static void execute() throws InterruptedException {
        ReferencedObject object=new ReferencedObject();
        ReferenceQueue queue=new ReferenceQueue();
        PhantomReference phantomReference=new PhantomReference(object,queue);
        checkReferenceQueueThread thread=new checkReferenceQueueThread(queue);
        thread.start();
        object=null;
//        莫名其妙,将while循环中的代码方入另一个线程这个幽灵引用就不入队列,将其放在主线程就入队列
//        OOMTestThread oomTestThread=new OOMTestThread();
//        oomTestThread.start();
        while (true){
            try {
                list.add(new byte[1024 * 100]);
                TMS.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }catch (Error error){

            }
        }
    }
}

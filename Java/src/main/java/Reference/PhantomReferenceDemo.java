package Reference;

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
* */
@Slf4j
public class PhantomReferenceDemo {
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
        TMS.sleep(5000);
        object=null;
        while (true){
            try {
                list.add(new byte[1024*100]);
                TMS.sleep(1000);
            }catch (Error e){

            }
        }

    }
}

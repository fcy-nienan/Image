package Reference;

import ThreadUtil.TMS;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
@Slf4j
public class PhantomReferenceDemo {
    public static void main (String args[]) throws InterruptedException {
        execute();
    }
    public static void execute() throws InterruptedException {
        ReferencedObject object=new ReferencedObject();
        ReferenceQueue queue=new ReferenceQueue();
        PhantomReference phantomReference=new PhantomReference(object,queue);
        System.out.println(phantomReference==null);
        System.out.println(phantomReference.get() == null);
        checkReferenceQueueThread thread=new checkReferenceQueueThread(queue);
        thread.start();
        System.out.println(phantomReference==null);
        System.out.println(phantomReference.get() == null);
        TMS.sleep(5000);
        System.gc();

    }
}

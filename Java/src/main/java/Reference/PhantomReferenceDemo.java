package Reference;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
@Slf4j
public class PhantomReferenceDemo {
    public static void main (String args[]) {
        execute();
    }
    public static void execute(){
        ReferencedObject object=new ReferencedObject();
        ReferenceQueue queue=new ReferenceQueue();
        PhantomReference phantomReference=new PhantomReference(object,queue);
        System.out.println(queue.poll());
        while (true){
            System.out.println(phantomReference.isEnqueued());
            log.info(String.valueOf(phantomReference.enqueue()));
            if (phantomReference.get()==null){
                log.info("object has been collected!");
                break;
            }
        }
    }
}

package Reference;

import Log.log;
import ThreadUtil.TMS;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
@Slf4j
public class checkReferenceQueueThread extends Thread{
    private ReferenceQueue queue;
    public checkReferenceQueueThread(ReferenceQueue queue){
        if (queue==null){
            throw new NullPointerException("queue can not be null!");
        }
        this.queue=queue;
    }

    @Override
    public void run () {
        int i=0;
        while (true){
            i++;
            Reference reference=queue.poll();
            if (reference!=null){
                log.info("reference is not null and count {} and reference enqueued!",i);
                if (reference.isEnqueued()){
                    log.info("reference has enqueued!");
                }
                if (reference.get()==null){
                    log.info("the object of the reference has been collected!");
                    break;
                }
            }
        }
    }
}

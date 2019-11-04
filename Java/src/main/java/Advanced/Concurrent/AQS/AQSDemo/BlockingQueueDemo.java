package Advanced.Concurrent.AQS.AQSDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/*
* add()
* offer()
* put()
*
* remove()
* poll()
* take()
*
* element()
* peek()
* 两个都是获取但不移除队列的头
* element没有元素则抛出异常
* peek返回null
* */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        testBlockingQueue();
    }
    public static void testBlockingQueue() throws InterruptedException {
        final BlockingQueue queue=new ArrayBlockingQueue(10);
        for(int i=0;i<10;i++){
            queue.add(i);
        }
        System.out.println(queue.element());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.size());

        queue.take();
        System.out.println(queue.size());
        ConcurrentHashMap map;
        Object o=new Object();

        queue.add(o);
        queue.offer(o);
        queue.put(o);

        queue.remove();
        queue.poll();
        queue.take();

        queue.element();
        queue.peek();
    }
}

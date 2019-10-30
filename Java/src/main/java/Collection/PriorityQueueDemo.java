package Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @descripiton:
 * PriortityQueue   优先级队列  不是按照普通队列的先进先出的方式
 *
 * 进入的元素需要实现Comparable接口或者直接传入一个Comparator比较器
 * 而是每个进入的元素都有一个优先级，然后内部通过大小堆进行排序，大堆还是小堆看比较器
 * 优先级高的先出队，如果是大堆则是优先级最大的先出队，如果是小堆则是优先级最小的先出队
 *
 * 内部实现是数组，queue[0]总是树的根节点
 *相关方法:
 *  add     添加元素
 *  offer   添加元素
 *  add内部是直接调用了offer方法
 *  poll    出队操作，size--
 *  peek    返回优先级最高的，内部是直接返回queue[0]元素，size不变
 *
 *
 * @author: fcy
 * @date: 2018-08-26  23:35
 */
public class PriorityQueueDemo {
    public static void main(String args[]) {
        testPriortityQueue();
    }
    public static void testPriortityQueue(){
        ArrayList list;
        PriorityQueue<user> queue=new PriorityQueue<>(new Comparator<user>() {
            @Override
            public int compare(user o1,user o2) {
                return o2.id-o1.id;
            }
        });
        Random random=new Random();
        for(int i=0;i<10;i++){
            queue.add(new user(random.nextInt(100),"fcy"+i));
        }
        System.out.println(queue.size());

        for (Object o : queue.toArray()) {
            System.out.println(o);
        }

        System.out.println("kkkkk");
        for(int i=0;i<10;i++){
            System.out.println(queue.poll());
        }
        System.out.println(queue.size());
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class user{
        int id;
        String name;
    }
}

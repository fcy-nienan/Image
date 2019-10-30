package Collection;

import java.util.*;

/**
 * @descripiton: ArrayList默认大小为10，扩容是newCapacity=old+old>>2
 * 查询是否包含使用的是equals方法
 * Vector默认大小也是10，他有个扩容增量，默认为0，也可以自己制定
 * 扩容的时候如果扩容增量为0，则新的容量为旧容量的两倍
 * 如果扩容增量不为0，则新的容量为旧的容量加上罗扩容增量
 * @author: fcy
 * @date: 2018-07-28  10:44
 */
public class ArrayListDemo {
    public static void main(String args[]) {
        testArrayList();
    }
    public static void testArrayList(){


        ArrayList<String> list=new ArrayList();
        list.contains(null);
        Vector vector=new Vector<>();
        LinkedList linkedList=new LinkedList();
        System.out.println(new Date().getMonth());
        Calendar calendar=Calendar.getInstance();
        int i = calendar.get(Calendar.MONTH);
        System.out.println(i);

    }
}

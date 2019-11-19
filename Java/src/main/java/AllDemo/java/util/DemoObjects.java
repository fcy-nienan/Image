package AllDemo.java.util;

import java.util.Objects;
import java.util.logging.Logger;
/*
* Objects.has(Object o)方法返回的并不是该对象的hashcode,他重新计算了
* Objects.deepEquals()方法比较的是两个数组是否相等,递归比较
*
* */
public class DemoObjects {
    private static Logger logger = Logger.getLogger(DemoObjects.class.getName());

    public static void main(String args[]) throws Exception {
        DemoObjects objects=new DemoObjects();
        System.out.println(Objects.hash(objects));
        System.out.println(Objects.hashCode(objects));
        System.out.println(objects.hashCode());

        Object[] objects1=new Object[]{new String[]{"ttt","kkk"},new int[]{1,2,3}};
        Object[] objects2=new Object[]{new String[]{"ttt","kkk"},new int[]{1,2,3}};
        System.out.println(Objects.deepEquals(objects1,objects2));
    }
}

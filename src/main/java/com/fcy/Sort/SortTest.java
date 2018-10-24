package com.fcy.Sort;

import java.util.Arrays;
import java.util.Comparator;

public class SortTest {
//    Comparable和Comparator的区别
//    多个对象之间的排序需要一个排序规则
//
//int,short,long,double等基本数据类型有默认的排序规则
//
//但自定义的类就没有，这个排序规则需要我们自己去定义
//
//如Student类有name，age，score三个属性，可以用不同的属性来排序
//
//comparable接口就是要排序的对象必须实现的接口
//comparator接口就是把排序规则独立出来，然后需要排序的时候在将某个排序规则传递过去
//    Student implements comparable
//
//
//class orderbyname implements comparator根据姓名排序
//
//class orderbyage implements comparator根据年龄排序
//
//使用方法:
//			Arrays.sort(allStudents,orderbyname_instance);
//			Arrays.sort(allStudents,orderbyage_instance);
//			注:
//				allStudnets所有的要排序的学生
//				orderbyname类实例，简写，下同
//如上此时可以将排序规则抽取出来，然后使用的时候在传递过去

    public static void main(String[] args) {
        student[] students=new student[3];
        for(int i=0;i<3;i++){
            students[i]=new student("aaa",i+12,i);
        }
    }
}

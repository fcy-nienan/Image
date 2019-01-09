package com.fcy.Concurrent.Thread;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListTest {
    public int a=10;
    public static int b=20;
    public void disss(){
        System.out.println("outer non-static method");
    }
    public static void staticdisss(){
        System.out.println("static outer method");
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ExecutorService executors= Executors.newCachedThreadPool();
        Vector v=new Vector();
        StringBuilder stringBuilder=new StringBuilder();
        StringBuffer stringBuffer=new StringBuffer();
        Stack stack=new Stack();
        CopyOnWriteArrayList copyOnWriteArrayList=new CopyOnWriteArrayList();
        LinkedList linkedList=new LinkedList();




//        序列化
//        transient 修饰的变量不会被序列化
//        hashmap的table数组就是transient修饰的,但还是可以被序列化,WHY?
//        hashmap实现了两个方法readObject(stream)和writeObject(stream)
//        ObjectOutputStream的writeObject方法会判断需要序列化的对象是否有上面两个方法,如果有的话就调用上面的
//        没有的话就调用ObjectOutputStream自己的defaultwriteObject(obj,stream)->defaultwriteField(obj,stream)
//        hashmap的writeObject()先调用defaultwriteObject方法写入没被transient修饰的变量,然后调用自己的方法写入table数组的数据
//        table数组有些地方是没有数据的,如果把整个table数组都写入的话会浪费很多空间,所以hashmap自己的方法写入的时候会判断是否为null
//        如果为null则不写入
        HashMap<String,String> hashMap=new HashMap<>();
        for(Integer i=0;i<10;i++){
            hashMap.put(i.toString(),i*i+"value");
        }
        ObjectOutputStream object=new ObjectOutputStream(new FileOutputStream("test"));
        object.writeObject(hashMap);
        object.writeInt(111);
        IOUtil.close(object);

        ObjectInputStream objectInput=new ObjectInputStream(new FileInputStream("test"));
        Map<String,String> readMap=(HashMap<String,String>)objectInput.readObject();
        int testint=objectInput.readInt();
        System.out.println(testint);
        Set<Map.Entry<String,String>> all=readMap.entrySet();
        for(Map.Entry<String,String> s:all){
            System.out.println("key:"+s.getKey()+"value:"+s.getValue());
        }



        User u=new User("nieann","fcy");
        Class clazz=u.getClass();
        clazz=User.class;
        //clazz=Class.forName("User");
        Method m1=clazz.getDeclaredMethod("display");
        System.out.println(m1.invoke(u));

        User u1=new ListTest.User();//这里并没有创建ListTest对象,只是创建了一个User对象
        User u2=new ListTest.User();
        System.out.println(u1==u2);
//        静态内部类的创建不需要外部类的支持,同时该静态内部类也没有外部类的this引用
//        普通内部类的创建需要先实例化外部类,普通内部类拥有外部类的this引用
//        静态类只能访问静态字段和方法
//        普通类可以访问非静态字段和静态方法
//        关键在于this
//        普通内部类不能有静态方法
//        静态类可以有静态和非静态方法
//        静态了没有this引用所以它的创建不依赖外部类可以直接创建,所以它不能使用外部类的非静态方法和变量
//        非静态内部类有this引用所以他的创建依赖外部类,所以它可以使用外部类的静态方法和非静态方法
//        在创建外部类的时候不会创建内部类,需要显式地去创建
//        静态内部类的创建   staticclass st=new OutClass().staticclass()
//        非静态内部类的创建 nonstaticclass nst=new OutClass().new nonstaticclass();
        ListTest lt=new ListTest();
        test t=lt.new test();


    }
    class test{
        public test1 getOuter(){
            disss();
            staticdisss();
            ListTest.this.staticdisss();
            System.out.println(a);
            System.out.println(b);
            return new ListTest().new test1();
        }
    }
    public class test1{
        public void s(){
            System.out.println("test1");
        }
    }
    public static class test2{

    }
    static class VectorAdd implements Callable<Integer>{
        private Vector v;
        public VectorAdd(Vector v){
            this.v=v;
        }
        public Integer call() {
            for(int i=0;i<10;i++){
                v.add(i);
            }
            return 10;
        }
    }
    static class User{
        private String name;
        private String password;
        public String display() {
            show();
            User.show();
//            System.out.println(a);静态内部类不可以访问非静态字段,因为他没有this引用
            System.out.println(b);

            return "User{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
        public static void show(){
            System.out.println("static");
        }
        public User() {
        }

        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
    static class IOUtil{
        public static void close(Closeable stream){
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

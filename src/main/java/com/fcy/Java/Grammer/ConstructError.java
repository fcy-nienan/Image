package com.fcy.Java.Grammer;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-13  19:04
 */
public class ConstructError {
    private String host="127.0.0.1";
    static {
//        System.out.println(port);illegal forward reference
    }
    private static final int port=8080;
    public ConstructError(String host){
        System.out.println(host);
    }
    public ConstructError(){
//        执行 B 之前不会初始化实例变量，构造函数没有执行对象实例是不存在的，
//        怎么会初始化换实例变量呢？在构造函数中可以使用实例变量是因为如果
//        一个 构造函数没有引用别的构造函数，
//        那么在进入该构造函数后就初始化实例了，
//        A 引用 B 代表将初始化的步骤交给 B 所以 B未执行的时候还没初始化
//        this(host); xx:compile error connot reference before supertype has been called
    }
}

package com.fcy.Java.Grammer.Variable;

public class Base {
    public static void main(String[] args) {
//        switchCase();
//        intAdd();
//        add();
//        d2();
//        d4();
//        d5();
//        d6();
    }
//    没有break不会跳出来
    public static void switchCase(){
        char c = 'A' ;
        int num = 10 ;
        switch(c) {
            case 'B' :
                num ++ ;
                System.out.println("B");
            case 'A' :
                num ++ ;
                System.out.println("A");
            case 'Y' :
                num ++ ;
                System.out.println("Y");
            default : {
                num--;
                System.out.println("default");
            }
        }
        System.out.println(num) ;
    }
//    如果两个操作数其中有一个是double类型，另一个操作就会转换为double类型。
// 　　否则，如果其中一个操作数是float类型，另一个将会转换为float类型。　
// 　否则，如果其中一个操作数是long类型，另一个会转换为long类型。　
// 　否则，两个操作数都转换为int类型
//    扩展的运算符有一个默认的强制转换,但是声明的变量类型是int类型,则最后还是会转换为int类型
    public static void intAdd(){
        int num = 2147483647 ;
        num += 2L ;//num=(int)(num+2l);强转
//        num=num+2l;//出错
        num+=2;
        System.out.println(num) ;
    }
//    num*2;
//    num=num+1;
//    num=num*2;
//    result:100
    public static void add(){
        int num = 50 ;
        num = num ++ * 2 ;
        System.out.println(num) ;
    }
//    i++和++i
//    result:5
    public static void d2(){
        int i = 1 ;
        int j = i++ ;
        if((i==(++j))&&((i++)==j)){
            i += j ;
        }
        System.out.println("i = "+i);
    }
//    拼接整数位字符串  不会相加
    public static void d4(){
        String str = "" ;
        for (int x = 0 ; x < 5 ; x ++) {
            str += x ;
        }
        System.out.println(str) ;
    }
//    float和double类型的数都有一个小数点位
//    result:10202.0   1.0
    public static void d5(){
        int x = 10 ;
        double y = 20.2 ;
        long z = 10L;
        String str = "" + x + y * z ;
        System.out.println(str) ;
        double f=1;
        System.out.println(f);
    }
//    可以认为default匹配所有
    public static void d6(){
        int i=9;
        switch(i){
            default:{
                System.out.println("default");
                break;
            }
            case 1:{
                System.out.println(1);
            }
            case 3:{
                System.out.println(9);
            }
        }
    }
}

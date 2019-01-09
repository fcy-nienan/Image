package com.fcy.Java.Grammer.Variable;

public class Base {
    public static void main(String[] args) {
//        switchCase();
//        intAdd();
//        add();
//        d1();
//        d2();
//        d3();
//        d4();
//        d5();
//        d6();
//        System.out.println(ss);
        new Base().stringTest(1,2,2);
    }
    public void stringTest(int aa,int bb,int cd){
        int c=10;
        c+=1l;
        if(c>12){
            c=3;
        }else{
            c=4;
        }
        int b=c>>2;
        b=c<<2;
        c=c>>>2;
        b=c&2;
    }
//    没有break不会跳出来
    public static void switchCase(){
        char c = 'A' ;
        int num = 10 ;
        switch(c) {
            case 'B' :
                num ++ ;
            case 'A' :
                num ++ ;
            case 'Y' :
                num ++ ;
                break ;
            default :
                num -- ;
        }
        System.out.println(num) ;
    }
//    如果两个操作数其中有一个是double类型，另一个操作就会转换为double类型。
// 　　否则，如果其中一个操作数是float类型，另一个将会转换为float类型。　
// 　否则，如果其中一个操作数是long类型，另一个会转换为long类型。　
// 　否则，两个操作数都转换为int类型
    public static void intAdd(){
        int num = 2147483647 ;
        num += 2L ;//num=(int)(num+2l);强转
//        num=num+2l;//出错
        System.out.println(num) ;
    }
//    num*2;
//    num=num+1;
//    num=num*2;
    public static void add(){
        int num = 50 ;
        num = num ++ * 2 ;
        System.out.println(num) ;
    }
    public static void LongAddInt(){
        long num = 100 ;

//        System.out.println(y);
//        int x = num + 2 ;
//        System.out.println(x) ;
    }
    public static void d1(){
        System.out.println(inc(10)+inc(8)+inc(-10));
        System.out.println(3+5+3);//编译期就会算出来了
    }
    public static int inc(int temp) {
        if (temp > 0) {
            return temp * 2 ;
        }
        return -1 ;
    }
//    i++和++i
    public static void d2(){
        int i = 1 ;
        int j = i++ ;
        if((i==(++j))&&((i++)==j))     {
            i += j ;
        }
        System.out.println("i = "+i);
    }
//    int类型溢出会自动装换为long型
    public static void d3(){
        int num = 2147483647 ;
        long temp = num + 2L ;
        System.out.println(num+" "+temp) ;
    }
//    拼接整数位字符串  不会相加
    public static void d4(){
        String str = "" ;
        for (int x = 0 ; x < 5 ; x ++) {
            str += x ;
        }
        System.out.println(str) ;
    }
    public static void d5(){
        int x = 10 ;
        double y = 20.2 ;
        long z = 10L;
        String str = "" + x + y * z ;
        System.out.println(str) ;
        String String=null;
        System.out.println(String);
    }
    /*
    * 可以这样命名
    *           String String=null;
    *
    * */
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
    public static int ss;
}

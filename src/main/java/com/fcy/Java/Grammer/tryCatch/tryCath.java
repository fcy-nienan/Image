package com.fcy.Java.Grammer.tryCatch;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-08-17  21:19
 */
public class tryCath {
    static int a;
    /*
    * return 可以可以放在四个地方 try catch finally return
    * 但同时存在的情况只有 try
    * try       try catch       try catch finally
    * catch finally     catch return
    *
    * */
    public static int testTryCath(){
        int x=10;
        try{
            x=11;
            int y=x/0;
            System.out.println("try");
            return x;
        }catch (Exception e){
            x=12;
            System.out.println("catch");
//            return x;
        }finally {
            x=13;
            System.out.println("finally");
//            return x;
        }
        return x;
    }
    public static void main(String args[]) {
//        System.out.println(testTryCath());
//        System.out.println(get1());
        testInnerFinally();
    }
    public static int get1(){
        a++;
        try{
            a++;
            System.out.println("try块"+a);
            return a/0;
        }catch(Exception e){

        }finally{
            a++;
            System.out.println("finally块"+a);
        }
        System.out.println("最后块");
        a++;
        a++;
        return a;
    }
    public static void testInnerFinally(){
        try{
            for(int i=0;i<1;i++){
                try{
                    System.out.println(1/0);
                }catch (Exception e){
                    System.out.println("Inner Exception!");
                }finally {
                    System.out.println("Inner Finally!");
                }
            }
        }catch (Exception e){
            System.out.println("Outer Exception!");
        }finally {
            System.out.println("Outer Finally!");
        }
    }
}

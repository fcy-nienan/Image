package com.fcy.Java.Grammer.Base;

/**
 * @descripiton: 尾递归测试
 * @author: fcy
 * @date: 2018-09-09  16:45
 */
public class TailRecursionTest {
    public static void main(String args[]) {
        test();
    }
    public static void test(){
        System.out.println(compute(0, 100));
        System.out.println(compute(100));
        System.out.println(factorialTailRecursion(0, 1000000).invoke());
    }
//    尾递归（尾递归还需要编译器的帮忙）
    public static int compute(int sum,int a){
        if(a==0)return sum;
        return compute(sum+a,--a);
    }
//    普通递归
    public static int compute(int a){
        if(a==0)return 0;
        else
            return a+compute(--a);
    }
//    使用lambda表达式实现尾递归
    public static TailRecursion<Integer> factorialTailRecursion(final int factorial, final int number) {
        if (number == 1)
            return TailInvoke.done(factorial);
        else
            return TailInvoke.call(() -> factorialTailRecursion(factorial + number, number - 1));
    }
}

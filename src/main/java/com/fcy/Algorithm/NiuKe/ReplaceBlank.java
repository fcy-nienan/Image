package com.fcy.Algorithm.NiuKe;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @descripiton: 将空格变为%20
 * @author: fcy
 * @date: 2018-08-20  22:50
 */
public class ReplaceBlank {
    public String replaceSpace(StringBuffer str) {
        char[] newChar=str.toString().toCharArray();
        for(int i=0;i<newChar.length;i++){
            char c=newChar[i];
            if((int)c==32){
                newChar[i]='%';
                newChar= Arrays.copyOf(newChar,newChar.length+2);
                for(int j=newChar.length-1;j>=i+2;j--){
                    newChar[j]=newChar[j-2];
                }
                newChar[i+1]='2';
                newChar[i+2]='0';
            }
        }
        return new String(newChar);
    }
    public static void main(String args[]) {
        ArrayList list;
        System.out.println(new ReplaceBlank().replaceSpace(new StringBuffer("12 34a sdf")));
        int a = 6;
        int b = 4;
        int x = a --- b;//2   5   4
        int y = a - -- b;//2  5   3
        int z = a -- - b;//2  4   3
        System.out.println(x+" "+y+" "+z);
        System.out.println(a+" "+b);
        System.out.println(3+-4);
        System.out.println(-++a);
        System.out.println(-a--);
        int c=4;
        c=c++*4;
        System.out.println(c);
        int num = 50 ;
        num = num ++ * 2 ;
        System.out.println(num) ;
        int j=0;
        for (int i = 0; i < 100; i++){
            j = j++;
        }
        System.out.println(j);
        int k=10;
        k=k++;
//        将k加载到栈顶，将局部变量表中的k自增1，将栈顶的值存入局部变量表的k值
//        所以最后k还是10
        System.out.println(k);

        /*
        * 正负   自增   乘除余    加减  移位  大小  相对
        *正负增减乘除余加减移位大小相等按位逻辑三目赋值
        * 正负自增减
        * 乘除余加减
        * 移位大小等
        * 按逻三赋值
        *
        *
        * >  <   !=   +   -   *   /   %   ?:   -  +   ++   --  =
        *
        * */
    }
}

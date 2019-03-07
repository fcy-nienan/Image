package com.fcy.Algorithm.LeetCode.String;

import scala.math.BigInt;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @descripiton:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 *    123
 *    456
 *      6  12  18
 *  5  10  15
 *4 8  12
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * @author: fcy
 * @date: 2019-03-02  18:41
 */
public class Multiply {
    public static void main(String[] args) {
        System.out.println(multiply("123","456"));
    }
    public static String multiply(String num1, String num2) {
        char[] value=new char[num1.length()+num2.length()];
        for(int i=num1.length()-1;i>=0;i--){
            for(int j=num2.length()-1;j>=0;j--){
                value[i+j+1]+=(num1.charAt(i)-'0')*(num2.charAt(j)-'0');
            }
        }
        int carry=0;
        for(int i=num1.length()+num2.length()-1;i>=0;i--){
            value[i]+=carry;
            carry=value[i]/10;
            value[i]%=10;
        }
        int j=0;
        while (j<value.length&&value[j]==0){
            j++;
        }
        int k=j;
        for(;j<value.length;j++){
            value[j]+='0';
        }
        System.out.println(Arrays.toString(value));
        return new String(value,k,value.length-k);
    }
    public static String multiply1(String s1,String s2){
        if (s1.equals("0")||s2.equals("0"))
            return "0";
        char[] value=new char[s1.length()+s2.length()];
        for(int i=s1.length()-1;i>=0;i--){
            for(int j=s2.length()-1;j>=0;j--){
                value[i+j+1]+=s1.charAt(i)-'0'*s2.charAt(j)-'0';
            }
        }
        int carry=0;
        for(int i=value.length-1;i>=0;i--){
            value[i]+=carry;
            carry=value[i]/10;
            value[i]%=10;
        }
        int start=0;
        while (start<value.length&&value[start]==0){
            start++;
        }
        for(int k=start;k<value.length;k++){
            value[k]+='0';
        }
        return new String(value,start,value.length-start);
    }
}

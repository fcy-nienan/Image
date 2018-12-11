package com.fcy.Algorithm.LeetCode;

import java.util.BitSet;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-08  23:05
 */
public class BitOperation {
    public static void main(String[] args) {
        And();
    }
    /**
    *@descripiton
     * and 1&1=1 1&0=0 0&0=0    只要有一个为0,就为0         0110&0000=0000  0110&1111=0110      可用来清空
     * or  1|1=1 1|0=1 0|0=0    只要有一个为1,就为1         0110|1111=1111  0110|0000=0110      可用来填充
     * xor 1^1=0 1^0=1 0^0=0    相同的为0,不同的为1         0110^1111=1001  0110^0000=0110      可用来反转
     * >>  右移   00001000>>2=00000010
     * <<  左移   00001000<<2=00100000
     * >>> 无符号右移 00001000>>>2=00000010  10001000>>>2=00100010
    *@author fcy
    *@date: 2018-12-08 23:39
    *@params []
    *@return void
    */
    public static void And(){
        String eight=Integer.toBinaryString(8);
        String nine=Integer.toBinaryString(9);
        System.out.println("8 binary:"+eight);
        System.out.println("9 binary:"+nine);
        int and=8&9;
        int or=8|9;
        int xor=8^9;
        System.out.println("And Result:8&9="+and+":"+Integer.toBinaryString(and));
        System.out.println("Or Result:8|9="+or+":"+Integer.toBinaryString(or));
        System.out.println("Xor Result:8^9="+xor+":"+Integer.toBinaryString(xor));
        int leftMove,rightMove,unsignedRightMove;
        leftMove=2<<2;
        rightMove=2>>2;
        unsignedRightMove=2>>>2;

    }
}

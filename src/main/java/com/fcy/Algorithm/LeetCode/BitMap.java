package com.fcy.Algorithm.LeetCode;

import java.util.HashMap;

/**
 * @descripiton:
 * BitSet   使用long数组存储位数    第一个long存储0-63,第二个存储64-127,以此类推
 * 使用场景:
 *  1.去重    将hash后的值映射到对应的位上,然后置为1,表示存在
 *          存在误判率,可以使用多个hash函数映射多位
 *  2,给定100亿个整数,找出只出现一次的整数
 *          为每一个整数分配两位,00出现0次,01出现一次,10出现多次,11不用
 *          10000000000*2=20000000000位
 *          20000000000/8/1024/1024/1024
 *                      bit   kb   mb   gb
 *          上面的位数计算不完整,100亿个这个整数,如果是int类型,则最大也就是21亿多点,
 *          所以最多需要大约42亿位
 *          4200000000/8/1024/1024/1024
 *                     bit kb  mb  gb
 * @author: fcy
 * @date: 2018-12-08  22:47
 */
public class BitMap {
    public static void main(String[] args) {
        System.out.println("3".hashCode());
        hash("3");
        int hash=hash("3");
        System.out.println(hash&8);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(4200000000l/8+"bit");
        System.out.println(4200000000l/8/1024+"kb");
        System.out.println(4200000000l/8/1024/1024+"mb");
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}

package com.fcy.Java.Collection;

import java.util.BitSet;

/**
 * @descripiton:
 * BitSet内部，是一个long[]数组，
 * 数组的大小由bitSet接收的最大数字决定，
 * 这个数组将数字分段表示[0,63],[64,127],[128,191]...。
 * 即long[0]用来存储[0,63]这个范围的数字的“存在性”，
 * long[1]用来存储[64,127]，依次轮推，这样就避免了位运算导致的冲突。
 *
 * bitset 可用来查重
 *
 * 内部原理
 *  找到对应的long
 *  找到对应的mask
 *  进行and,or,xor等操作
 * @author: fcy
 * @date: 2018-12-09  17:53
 */
public class BitSetDemo {
    public static void main(String[] args) {
        demo();
    }
    public static void demo(){
        BitSet bitSet=new BitSet(1);
        String urlPrefix="http://www.xicidaili.com/nn/";
        for(int i=0;i<12;i++){
            bitSet.set(hash(urlPrefix+i));
        }

        System.out.println(bitSet);
        System.out.println("位图中着色位数:"+bitSet.cardinality());
        System.out.println(bitSet.length());//最高索引位加一
        System.out.println(bitSet.size());///bit位数大小
        System.out.println(bitSet.get(hash(urlPrefix+3)));
        System.out.println(bitSet.get(hash(urlPrefix+89)));
        System.out.println(bitSet.get(hash(urlPrefix+12)));

    }
    private static int hash(Object o){
        int hash=o.hashCode();
        return Math.abs(hash%64);
    }
}

package com.fcy.Util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-15  16:27
 */
public class RandomThread {
    public static void main(String[] args) {
        Random random=new Random();
        for(int i=0;i<10;i++){
            System.out.println(random.nextInt(10));
        }
        //多线程安全随机数

        /*
        * Random使用原子遍历的CAS保证多个线程获取的是不同的随机数种子
        * ThreadLocalRandom通过在每个线程中保存long类型的一个随机数种子,每个线程使用自己的
        *
        * */
        ThreadLocalRandom localRandom=ThreadLocalRandom.current();
        for(int i=0;i<10;i++){
            System.out.println(localRandom.nextInt());
        }
    }

}

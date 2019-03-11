package com.fcy.Concurrent.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-11  22:03
 */
public class ParallelStream {
    public static void main(String[] args) {
        List<String> list=new ArrayList();
        for(int i=0;i<10000000;i++){
            list.add(UUID.randomUUID().toString());
        }
        long start=System.nanoTime();
        list.stream().sorted();
        long end=System.nanoTime();
        System.out.println("cost:"+(end-start));
    }
}

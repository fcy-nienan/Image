package com.fcy.Java.Concurrent.Thread;

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
        int count=1000000;
        long start,end;
        List<String> list=new ArrayList<>();
        fullData(list,count);

        start=System.nanoTime();
        list.stream().sorted();
        end=System.nanoTime();
        System.out.println("cost:"+(end-start));

        list.clear();
        fullData(list,count);

        start=System.nanoTime();
        list.parallelStream().sorted();
        end=System.nanoTime();
        System.out.println("cost:"+(end-start));
    }
    private static void fullData(List list,int count){
        for(int i=0;i<count;i++){
            list.add(UUID.randomUUID().toString());
        }
    }
}

package com.fcy.BigData.Spark;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-23  1:32
 */
public class FunctionDemo {
    private static List<Integer> data=new ArrayList<>();
    private static List<List<Integer>> datas=new ArrayList<>();
    static {
        Random random=new Random();
        for(int i=0;i<10;i++){
            data.add(random.nextInt(100));
        }
        for(int i=0;i<10;i++){
            datas.add(data);
        }
        System.out.println(data);
    }
    public static void main(String[] args) {
//        Map();
//        Filter();
        flatMap();
    }
    public static void Map(){
        data=data.stream().map(d->d+1).collect(toList());
        System.out.println(data);
    }
    public static void Filter(){
        data=data.stream().filter(e->e>50).collect(toList());
        System.out.println(data);
    }
    public static void flatMap(){
        Stream<Integer> stream=datas.stream().flatMap(x->x.stream());
        stream.forEach(e->{
            System.out.print(e+"\t");
        });
        List<Integer> list=datas.stream().flatMap(e->e.stream().map(s->s+1)).collect(toList());

    }
}

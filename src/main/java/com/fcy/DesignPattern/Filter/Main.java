package com.fcy.DesignPattern.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:48
 */
public class Main {
    public static void main(String[] args) {
        List<Person> people=new ArrayList<>();
        for(int i=0;i<10;i++){
            Random random=new Random();
            Person p=new Person("fcy",random.nextInt(100));
            people.add(p);
        }
        FilterManager manager=new FilterManager();
        manager.addFilter(new AgeFilter());
        manager.addFilter(new NameFilter());
        people=manager.start(people);
        System.out.println(people);


    }

}

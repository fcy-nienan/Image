package com.fcy.Java.DesignPattern.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:48
 */
public class AgeFilter implements Filter {
    @Override
    public List<Person> filter(List<Person> personList) {
        List<Person> result=new ArrayList<>();
        personList.forEach(e->{
            if (e.getAge()>20){
                result.add(e);
            }
        });
        return result;
    }
}

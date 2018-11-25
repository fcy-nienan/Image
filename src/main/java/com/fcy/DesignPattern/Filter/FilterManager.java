package com.fcy.DesignPattern.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:53
 */
public class FilterManager {
    private List<Filter> filters=new ArrayList<>();
    private List<Person> people;
    public void addFilter(Filter filter){
        filters.add(filter);
    }
    public List<Person> start(List<Person> personList){
        people=personList;
        filters.forEach(e->{
            people=e.filter(people);
        });
        return people;
    }

}

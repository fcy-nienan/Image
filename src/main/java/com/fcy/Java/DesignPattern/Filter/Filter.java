package com.fcy.Java.DesignPattern.Filter;

import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:47
 */
public interface Filter {
    List<Person> filter(List<Person> personList);
}

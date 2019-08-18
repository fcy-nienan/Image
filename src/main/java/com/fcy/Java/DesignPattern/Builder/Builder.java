package com.fcy.Java.DesignPattern.Builder;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:14
 */
public interface Builder {
    void buildDrink();
    void buildBurger();
    void buildChicken();
    StringBuilder getResult();
}

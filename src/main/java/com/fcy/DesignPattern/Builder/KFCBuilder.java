package com.fcy.DesignPattern.Builder;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:16
 */
public class KFCBuilder implements Builder {
    private StringBuilder builder=new StringBuilder();
    @Override
    public void buildDrink() {
        builder.append("KFC Drink");
    }

    @Override
    public void buildBurger() {
        builder.append("KFC Burger");
    }

    @Override
    public void buildChicken() {
        builder.append("KFC Chicken");
    }

    @Override
    public StringBuilder getResult() {
        return builder;
    }
}

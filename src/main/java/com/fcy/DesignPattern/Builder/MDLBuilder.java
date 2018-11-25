package com.fcy.DesignPattern.Builder;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:31
 */
public class MDLBuilder implements Builder {
    private StringBuilder builder=new StringBuilder();
    @Override
    public void buildDrink() {
        builder.append("MDL Drink");
    }

    @Override
    public void buildBurger() {
        builder.append("MDL Burger");
    }

    @Override
    public void buildChicken() {
        builder.append("MDL Chicken");
    }

    @Override
    public StringBuilder getResult() {
        return builder;
    }
}

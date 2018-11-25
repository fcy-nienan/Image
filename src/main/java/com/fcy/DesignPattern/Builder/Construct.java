package com.fcy.DesignPattern.Builder;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:18
 */
public class Construct {
    private Builder builder;
    public Construct(Builder builder){
        this.builder=builder;
    }
    public void construct(){
        builder.buildDrink();
        for(int i=0;i<3;i++){
            builder.buildChicken();
        }
        builder.buildBurger();
    }
}

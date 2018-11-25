package com.fcy.DesignPattern.Strategy;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:36
 */
public class Main {
    public static void main(String[] args) {
        AbstractCalculator calculator=new AbstractCalculator();
        calculator.compute();

        SpecialStrategy strategy=new SpecialStrategy();
        calculator.setStrategyStep(strategy);
        calculator.compute();
    }
}

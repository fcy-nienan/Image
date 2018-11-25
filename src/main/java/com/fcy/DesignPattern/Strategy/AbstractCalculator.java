package com.fcy.DesignPattern.Strategy;

import lombok.Setter;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:31
 */
public class AbstractCalculator {
    @Setter
    private StrategyStep strategyStep;
    public void compute(){
        if (strategyStep==null){
            strategyStep=new DefaultStrategy();
        }
        step1();
        for(int i=0;i<2;i++){
            int x=strategyStep.calculator();
            if (x>10){
                step3(x);
            }else{
                step2();
            }
        }
    }
    private void step1(){
        System.out.println("step 1");
    }
    private void step2(){
        System.out.println("step 2");
    }
    private void step3(int x){
        System.out.println("step 3:"+x);
    }
}

package com.fcy.DesignPattern.NullObject;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:23
 */
public class RealCustomer extends AbstractCustomer {
    public RealCustomer(String name){
        this.name=name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isNil() {
        return false;
    }
}

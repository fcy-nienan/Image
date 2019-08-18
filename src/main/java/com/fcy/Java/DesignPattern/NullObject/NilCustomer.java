package com.fcy.Java.DesignPattern.NullObject;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:25
 */
public class NilCustomer extends AbstractCustomer {
    @Override
    public String getName() {
        return "Not a valid Customer";
    }

    @Override
    public boolean isNil() {
        return true;
    }
}

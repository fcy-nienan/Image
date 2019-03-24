package com.fcy.DesignPattern.NullObject;
/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:26
 */
public class Main {
    public static void main(String[] args) {
        AbstractCustomer customer=CustomerFactory.getCustomer("fcy");
        AbstractCustomer customer1=CustomerFactory.getCustomer("nie");

        System.out.println(customer.getName());
        System.out.println(customer1.getName());
    }

}

package com.fcy.DesignPattern.NullObject;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:26
 */
public class CustomerFactory {
    private static String[] names={"fcy","nienan"};
    public static AbstractCustomer getCustomer(String name){
        for(int i=0;i<names.length;i++){
            if (names[i].equals(name)){
                return new RealCustomer(name);
            }
        }
        return new NilCustomer();
    }

}

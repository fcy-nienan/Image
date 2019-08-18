package com.fcy.Java.DesignPattern.Factory.FactoryFactory;

import com.fcy.Java.DesignPattern.Factory.AbstractFactory.LinuxFactory;
import com.fcy.Java.DesignPattern.Factory.AbstractFactory.WindowsFactory;
import com.fcy.Java.DesignPattern.Factory.Factory;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  0:56
 */
public class FactoryProducer {
    public static Factory getFactory(String type){
        if (type.toLowerCase().equals("linux")){
            return new LinuxFactory();
        }else if(type.toLowerCase().equals("windows")){
            return new WindowsFactory();
        }else{
            return null;
        }
    }
}

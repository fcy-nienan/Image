package com.fcy.DesignPattern.Factory.AbstractFactory;

import com.fcy.DesignPattern.Factory.Factory;
import com.fcy.DesignPattern.Factory.NormalFactory.ComputerSystem;
import com.fcy.DesignPattern.Factory.NormalFactory.WindowComputerSystem;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  0:44
 */
public class WindowsFactory implements Factory {

    @Override
    public ComputerSystem produce() {
        return new WindowComputerSystem();
    }
}

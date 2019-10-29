package DesignPattern.Factory.AbstractFactory;

import DesignPattern.Factory.Factory;
import DesignPattern.Factory.NormalFactory.ComputerSystem;
import DesignPattern.Factory.NormalFactory.WindowComputerSystem;

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

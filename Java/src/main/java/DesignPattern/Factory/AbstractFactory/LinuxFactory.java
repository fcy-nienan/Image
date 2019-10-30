package DesignPattern.Factory.AbstractFactory;

import DesignPattern.Factory.Factory;
import DesignPattern.Factory.NormalFactory.ComputerSystem;
import DesignPattern.Factory.NormalFactory.LinuxComputerSystem;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  0:41
 */
public class LinuxFactory implements Factory {

    @Override
    public ComputerSystem produce() {
        return new LinuxComputerSystem();
    }
}

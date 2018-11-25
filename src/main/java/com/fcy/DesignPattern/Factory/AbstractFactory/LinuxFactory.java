package com.fcy.DesignPattern.Factory.AbstractFactory;

import com.fcy.DesignPattern.Factory.Factory;
import com.fcy.DesignPattern.Factory.NormalFactory.ComputerSystem;
import com.fcy.DesignPattern.Factory.NormalFactory.LinuxComputerSystem;

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

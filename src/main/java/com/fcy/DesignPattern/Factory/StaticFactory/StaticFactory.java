package com.fcy.DesignPattern.Factory.StaticFactory;

import com.fcy.DesignPattern.Factory.Factory;
import com.fcy.DesignPattern.Factory.NormalFactory.ComputerSystem;
import com.fcy.DesignPattern.Factory.NormalFactory.LinuxComputerSystem;
import com.fcy.DesignPattern.Factory.NormalFactory.WindowComputerSystem;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  0:38
 */
public class StaticFactory implements Factory {
    public static ComputerSystem getLinux(){
        return new LinuxComputerSystem();
    }
    public static ComputerSystem getWindows(){
        return new WindowComputerSystem();
    }

    @Override
    public ComputerSystem produce() {
        return null;
    }
}

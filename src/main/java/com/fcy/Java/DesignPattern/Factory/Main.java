package com.fcy.Java.DesignPattern.Factory;


import com.fcy.Java.DesignPattern.Factory.AbstractFactory.LinuxFactory;
import com.fcy.Java.DesignPattern.Factory.AbstractFactory.WindowsFactory;
import com.fcy.Java.DesignPattern.Factory.FactoryFactory.FactoryProducer;
import com.fcy.Java.DesignPattern.Factory.NormalFactory.ComputerSystem;
import com.fcy.Java.DesignPattern.Factory.NormalFactory.NormalFactory;
import com.fcy.Java.DesignPattern.Factory.StaticFactory.StaticFactory;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:00
 */
public class Main {
    public static void main(String[] args) {
        StaticFactory0();
        NormalFactory0();
        AbstractFactory0();
        FactoryFactory0();
    }
    public static void StaticFactory0(){
        ComputerSystem linux= StaticFactory.getLinux();
        ComputerSystem windows=StaticFactory.getWindows();
        linux.dis();
        windows.dis();
    }
    public static void NormalFactory0(){
        ComputerSystem linux= NormalFactory.getInstance("linux");
        ComputerSystem windows=NormalFactory.getInstance("windows");
        linux.dis();
        windows.dis();
    }
    public static void AbstractFactory0(){
        ComputerSystem linux=new LinuxFactory().produce();
        ComputerSystem windows=new WindowsFactory().produce();
        linux.dis();
        windows.dis();
    }
    public static void FactoryFactory0(){
        Factory linuxF=FactoryProducer.getFactory("linux");
        Factory windowsF=FactoryProducer.getFactory("windows");
        ComputerSystem linux=linuxF.produce();
        ComputerSystem windows=windowsF.produce();
        linux.dis();
        windows.dis();
    }
}

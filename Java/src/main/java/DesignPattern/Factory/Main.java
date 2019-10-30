package DesignPattern.Factory;


import DesignPattern.Factory.AbstractFactory.LinuxFactory;
import DesignPattern.Factory.AbstractFactory.WindowsFactory;
import DesignPattern.Factory.FactoryFactory.FactoryProducer;
import DesignPattern.Factory.NormalFactory.ComputerSystem;
import DesignPattern.Factory.NormalFactory.NormalFactory;
import DesignPattern.Factory.StaticFactory.StaticFactory;

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

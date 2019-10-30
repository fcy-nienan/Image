package DesignPattern.Factory.NormalFactory;

import DesignPattern.Factory.Factory;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  0:35
 */
public class NormalFactory implements Factory {
    public static ComputerSystem getInstance(String type){
        switch(type.toLowerCase()){
            case "linux":return new LinuxComputerSystem();
            case "windows":return new WindowComputerSystem();
            default:
                System.out.println("No This System!");
                return null;
        }
    }

    @Override
    public ComputerSystem produce() {
        return null;
    }
}

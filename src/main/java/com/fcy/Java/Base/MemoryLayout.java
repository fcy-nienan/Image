package com.fcy.Java.Base;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-25  23:23
 */
class A{
    long i=0;
    boolean flag=false;
}

public class MemoryLayout{

    public static void main(String[] args){

        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

}

package com.fcy.DesignPattern.Builder;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:21
 */
public class Main {
    public static void main(String[] args) {
        Builder builder=new KFCBuilder();
        Construct construct=new Construct(builder);
        construct.construct();
        System.out.println(builder.getResult());
        builder=new MDLBuilder();
        construct=new Construct(builder);
        construct.construct();
        System.out.println(builder.getResult());
    }
}

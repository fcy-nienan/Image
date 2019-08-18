package com.fcy.Java.DesignPattern.Memorandum;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:17
 */
public class Main {
    public static void main(String[] args) {
        EntityObject object=new EntityObject("fcy",21);
        Backup backup=new Backup();
        object.record(backup);
        System.out.println(object);

        object.setName("nienan");
        object.setAge(23);
        System.out.println(object);

        object.backup(backup);
        System.out.println(object);
    }
}

package com.fcy.DesignPattern.Proxy.Dynamic.Java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-04-23  12:27
 */
public class Main {
    public static void main(String[] args) {
        Person person=new Man();
        InvocationHandler handler=new PersonProxy(person);
        Person proxyObject= (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),new Class[]{Person.class},handler);
        proxyObject.eat();
    }
}

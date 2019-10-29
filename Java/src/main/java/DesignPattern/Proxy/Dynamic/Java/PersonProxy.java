package DesignPattern.Proxy.Dynamic.Java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-04-23  12:25
 */
public class PersonProxy implements InvocationHandler {
    private Person person;
    public PersonProxy(Person p){
        this.person=p;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("eat before");
        Object result=method.invoke(person,args);
        System.out.println("eat after");
        return result;
    }
}

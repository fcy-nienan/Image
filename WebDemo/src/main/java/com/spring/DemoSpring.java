package com.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.logging.Logger;

public class DemoSpring {
    private static Logger logger = Logger.getLogger(DemoSpring.class.getName());

    public static void main(String args[]) throws Exception{
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("application.xml");
        Object beanPostProcess = applicationContext.getBean("beanPostProcess");
        Object beanFactoryProcess = applicationContext.getBean("beanFactoryPostProcess");
        Object user1 = applicationContext.getBean("user");
        System.out.println(user1.toString());

    }
}

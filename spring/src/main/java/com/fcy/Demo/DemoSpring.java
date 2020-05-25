package com.fcy.Demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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

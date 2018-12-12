package com.fcy.JMX;


<<<<<<< HEAD
//import com.sun.jdmk.comm.HtmlAdaptorServer;
=======
>>>>>>> zr

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

public class Main {
    public static void main(String[] args) throws Exception{
        MBeanServer ms= MBeanServerFactory.createMBeanServer();
        ms=MBeanServerFactory.newMBeanServer();
        Console consoleBase=new Console();
        ObjectName consoleObj=new ObjectName("MyappMBean:state=consoleBase");
        ms.registerMBean(consoleBase,consoleObj);

//        HtmlAdaptorServer server=new HtmlAdaptorServer();
<<<<<<< HEAD
        ObjectName objectName=new ObjectName("ConsoleAgent:state=htmladpter,port=8082");
//        ms.registerMBean(server,objectName);
//
//        server.start();
        while(true){
            Thread.sleep(3000);
            System.out.println(consoleBase.getState());
        }
=======
//        ObjectName objectName=new ObjectName("ConsoleAgent:state=htmladpter,port=8082");
//        ms.registerMBean(server,objectName);
////
//        server.start();
//        while(true){
//            Thread.sleep(3000);
//            System.out.println(consoleBase.getState());
//        }
>>>>>>> zr
    }
}

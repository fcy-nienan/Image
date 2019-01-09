package com.fcy.Util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class log {
    private static String getInfo(String msg){
        Thread t=Thread.currentThread();
        StackTraceElement element=t.getStackTrace()[3];
        String content="";
        String className=element.getClassName();
        String methodName=element.getMethodName();
        content+=className;
        content+=":"+methodName;
        content+=":"+msg;
        return content;
    }
    /**
    *@descripiton 只显示上一层方法调用
    */
    public static void info(String msg){
        System.out.println(transferCount.getCount()+getInfo(msg));
    }
    /**
    *@descripiton 显示多层调用栈
    */
    public static void infoDeep(String msg){
        Thread t=Thread.currentThread();
        StackTraceElement[] element=t.getStackTrace();
        String content="";
        for (StackTraceElement traceElement : element) {
            int line=traceElement.getLineNumber();
            String methodName=traceElement.getMethodName();
            content+=":"+methodName+":"+line;
        }
        content+=msg;
        System.out.println(t.getId()+"::"+content);
    }

    /**
    *@descripiton 显示多线程打印不存文件
    */
    public static void infoToScreen(String msg){
        long id=Thread.currentThread().getId();
        System.out.println(transferCount.getCount()+":"+id+":"+msg);
    }
    /**
    *@descripiton 显示多线程打印顺序并按线程id存入不同的文件中
    */
    public static void infoToFile(String msg){
        Thread t=Thread.currentThread();
        File file=new File("H:\\Thread"+t.getId()+".txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String content=getInfo(msg);
        FileWriter writer=null;
        try {
            writer=new FileWriter(file,true);

            writer.append(transferCount.getCount()+":");
            writer.append(content);
            writer.append("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
                try {
                writer.close();
            } catch (IOException e) {
            }
        }
    }

}

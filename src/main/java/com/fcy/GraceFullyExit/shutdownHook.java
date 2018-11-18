package com.fcy.GraceFullyExit;

import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-10  21:38
 */
public class shutdownHook implements Runnable{
    @Override
    public void run() {
        System.out.println("ShutdownHook execute start...");
        System.out.print("Netty NioEventLoopGroup shutdownGracefully...");
        try {
            TimeUnit.SECONDS.sleep(10);//模拟应用进程退出前的处理操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ShutdownHook execute end...");
        System.out.println("Sytem shutdown over, the cost time is 10000MS");
    }
}

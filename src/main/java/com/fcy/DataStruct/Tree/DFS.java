package com.fcy.DataStruct.Tree;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-21  7:32
 */
public class DFS {
    public static void main(String[] args) throws InterruptedException {

        DFS dfs=new DFS();
        Runtime runtime=Runtime.getRuntime();
        System.out.println(runtime.freeMemory());
        System.out.println(runtime.maxMemory());
        System.out.println(runtime.totalMemory());
        runtime.runFinalization();
        dfs=null;
        System.gc();
        Thread.sleep(4000);
        System.out.println("df");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("is collected!");
        BlockingQueue queue=new ArrayBlockingQueue(10);
        queue.take();
        System.out.println("is collected!");
    }
}

package com.fcy.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Filter;
public class ForkInJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis(),end;
        ForkJoinTask forkJoinTask=new ForkJoinTask(1,100000000);
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(forkJoinTask);
        forkJoinTask.join();
        System.out.println(forkJoinTask.get());
        end=System.currentTimeMillis();
        System.out.println("花费时间:"+(float)(end-start));
    }
    static class ForkJoinTask extends RecursiveTask<Integer>{
        private static final int THRESHOLD=10000;
        private int start;
        private int end;

        public ForkJoinTask(int start,int end) {
            this.start=start;
            this.end = end;
        }

        public Integer compute(){
//        标识是否分割任务
            boolean flag=(start-end)<THRESHOLD;
//        计算结果
            int sumer=0;
            if(flag){//如果不用分割  直接计算结果
                System.out.println("start compute");
                for(int i=start;i<end;i++){
                    sumer+=i;
                }
            }else{//否则分割任务
                int middle=(start+end)/2;
                ForkJoinTask leftTask=new ForkJoinTask(start,middle);
                ForkJoinTask rightTask=new ForkJoinTask(middle+1,end);
//            开始任务
                leftTask.fork();
                rightTask.fork();
//                invokeAll(leftTask,rightTask);
//            等待任务完成
                int leftResult=leftTask.join();
                int rightResult=rightTask.join();
//            合并计算结果
                sumer=leftResult+rightResult;
            }
            return sumer;
        }
    }
}

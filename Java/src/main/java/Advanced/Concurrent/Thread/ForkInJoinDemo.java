package Advanced.Concurrent.Thread;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/*
* 计算1到100000000000的sum
*
932356074711512064
forkJoin cost:16秒
*
*
932356074711512064
normal cost:26秒
*
* */
public class ForkInJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=1;
        long end=100000000000l;
        StopWatch watch=new StopWatch();
        ForkJoinTask forkJoinTask=new ForkJoinTask(start,end);
        ForkJoinPool pool = new ForkJoinPool(8);
        watch.start();
        System.out.println(pool.invoke(forkJoinTask));
        watch.stop();
        System.out.println("forkJoin cost:"+watch.getTime());
        watch.reset();

        long sum=0;
        watch.start();
        for (long i=start;i<=end;i++){
            sum+=i;
        }
        watch.stop();
        System.out.println(sum);
        System.out.println("normal cost:"+watch.getTime());
    }
    static class ForkJoinTask extends RecursiveTask<Long>{
        private static final int THRESHOLD=10000000;
        private long start;
        private long end;

        public ForkJoinTask(long start,long end) {
            this.start=start;
            this.end = end;
        }

        public Long compute(){
//        标识是否分割任务
            boolean flag=(end-start)<THRESHOLD;
//        计算结果
            long sumer=0;
            if(flag){//如果不用分割  直接计算结果
//                System.out.println("start compute");
                for(long i=start;i<=end;i++){
                    sumer+=i;
                }
            }else{//否则分割任务
                long middle=(start+end)/2;
                ForkJoinTask leftTask=new ForkJoinTask(start,middle);
                ForkJoinTask rightTask=new ForkJoinTask(middle+1,end);
//            开始任务
                leftTask.fork();
                rightTask.fork();
//                invokeAll(leftTask,rightTask);
//            等待任务完成
                long leftResult=leftTask.join();
                long rightResult=rightTask.join();
//            合并计算结果
                sumer=leftResult+rightResult;
            }
            return sumer;
        }
    }
}

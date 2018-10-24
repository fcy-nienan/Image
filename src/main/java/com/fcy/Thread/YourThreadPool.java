package com.fcy.Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class YourThreadPool {
    /**
     * 最大线程数
     */
    private static final int MAX_WORK_THREAD = 10;
    /**
     * 最小线程数
     */
    private static final int MIN_WORK_THREAD = 1;
    /**
     * 默认的线程数
     */
    private static final int DEFAULT_WORK_THREAD = 5;

    /**
     * 工作列表（无序）
     */
    private final LinkedList<Runnable> jobQueue = new LinkedList<>();

    /**
     * 工作者线程
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    /**
     * 工作线程数
     */
    private int workerNum = DEFAULT_WORK_THREAD;

    /**
     * 线程编号
     */
    private AtomicLong threadNum = new AtomicLong();

    public YourThreadPool() {
        initWorks(DEFAULT_WORK_THREAD);
    }

    public YourThreadPool(int num) {
        if (num > MAX_WORK_THREAD) {
            workerNum = MAX_WORK_THREAD;
        } else if (workerNum < MIN_WORK_THREAD) {
            workerNum = MIN_WORK_THREAD;
        } else {
            workerNum = num;
        }
        initWorks(workerNum);
    }

    /**
     * 初始化工作线程
     *
     * @param num
     */
    private void initWorks(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread =
                    new Thread(worker, "THPOLL-WORKER-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    public void execute(Runnable job) {

        if (job != null) {
            synchronized (jobQueue) {
                // 加入工作线程队列
                jobQueue.add(job);

                // 尝试唤醒线程
                jobQueue.notify();
            }

        }
    }

    public void shutDown() {
        // 线程关闭循环
        for (Worker worker : workers) {
            worker.shutDown();
        }
        // 全部唤醒
        synchronized (jobQueue) {
            jobQueue.notifyAll();
        }
    }

    public void addWorkThread(int num) {
        synchronized (jobQueue) {
            if (num + this.workerNum > MAX_WORK_THREAD) {
                num = MAX_WORK_THREAD - this.workerNum;
            }
            initWorks(num);
            this.workerNum += num;
        }
    }

    public void reduceWorkThread(int num) throws Exception {
        synchronized (jobQueue) {
            if (num >= this.workerNum) {
                throw new Exception();
            }
            int count = num;
            int succCount = 0;
            while (count > 0) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutDown();
                    count--;
                    succCount++;
                }
            }
            this.workerNum -= succCount;
        }

    }

    public int getSize() {
        return jobQueue.size();
    }

    private class Worker implements Runnable {

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Runnable job = null;
                synchronized (jobQueue) {
                    while (jobQueue.isEmpty() && running) {
                        try {
                            jobQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (!jobQueue.isEmpty()) {
                        job = jobQueue.removeFirst();
                    }
                }
                // 如果此时线程池已经被关闭，则忽略所有任务
                // 现实情况可能有其他操作
                if (job != null && running) {
                    try {
                        job.run();
                        System.out.println("JOB=" + Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void shutDown() {
            running = false;
        }
    }

    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(i+"  "+Thread.currentThread().getId());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(()->{
            for(int i=10;i<20;i++){
                System.out.println(i+"  "+Thread.currentThread().getId());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        YourThreadPool yourThreadPool=new YourThreadPool();
        yourThreadPool.execute(t1);
        yourThreadPool.execute(t2);
    }
}

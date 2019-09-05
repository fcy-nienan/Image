package com.fcy.Java.Concurrent.AQS.pratice1;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
*多个线程互相等待，一起到达了某个点后一起执行
*
* 所有玩家准备好了之后才开始游戏
*
* */
public class CyclicBarrierDemo1 {
//    是否需要加volatile修饰符？
    private static volatile AtomicInteger currentEnterNum=new AtomicInteger(0);//当前进入游戏人数
    private static final int regularNum=4;//一把游戏应该有几个人
    public static void main(String args[]) {
        int playerNum=2;
        ThreadPoolExecutor executor=new ThreadPoolExecutor(30,50,60, TimeUnit.SECONDS,new ArrayBlockingQueue(500));
        CyclicBarrier barrier=new CyclicBarrier(playerNum);
        for(int i=0;i<3;i++){
            executor.submit(new player("player"+i,barrier,3));
        }
        executor.submit(new player("not connected player3",barrier,15));
    }
    static class player implements Runnable{
        private String playerName;
        private CyclicBarrier barrier;
        private long netCon;
        public player(String playerName,CyclicBarrier barrier,long netCon){
            this.barrier=barrier;
            this.playerName=playerName;
            this.netCon=netCon;
        }
        @Override
        public void run() {
            dis("选择英雄和配置符文",3);
            dis("网络连接中",netCon);
            //一个玩家每连接上可以判断，如果有多个玩家每连接上呢，那么他们也是等待其他再一起连还是不等待直接进入？
            try {
                if (currentEnterNum.get()==0) {//如果当前没有人则等待一起连接
                    barrier.await(10, TimeUnit.SECONDS);
                }else if (currentEnterNum.get()<regularNum){//说明有人进入了
                    int waiting=barrier.getNumberWaiting();
                    if (waiting == 1) {//如果当前等待人只有自己
                        dis("重连直接进入游戏!");
                    }else{//否则，等待其他人
                        barrier.await(10, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException e) {
                dis("游戏被强行停止!");
                return;
            } catch (BrokenBarrierException e) {
                dis("游戏运行异常!");
                return;
            } catch (TimeoutException e) {
                dis("等待其他玩家连接异常!提前进入游戏");
            }
            currentEnterNum.addAndGet(1);
            while (true){
                dis("游戏中");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    dis("游戏被强行停止!选择是否上传错误信息?");
                    return;
                }
            }
        }
        private void dis(String action){
            System.out.printf("%s : %s \r\n",playerName,action);
        }
        private void dis(String action,long time){
            dis(action);
            try {
                Thread.sleep(time*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

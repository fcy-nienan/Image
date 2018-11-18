package com.fcy.Zookeeper.DisLock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-14  23:56
 */
public class ZooDataSource {
    private static final String host="127.0.0.1";
    private static final int port=2181;
    private static final String path="/lock";
    private static ZooKeeper zooKeeper;
    private static List<Watcher> watchers=new ArrayList<>();
    static {
        try {
            zooKeeper=new ZooKeeper(host+":"+port,2000,null);
        } catch (IOException e) {
            System.out.println("Zookeeper连接失败..."+e.getMessage());
        }
    } public static void register(Watcher watcher){
        zooKeeper.register(watcher);
    }

    public static void main(String[] args) throws InterruptedException {
        lock();
        Thread.sleep(3000);
        unlock();
        Thread.sleep(30000);
    }
    public static void lock(){
        boolean flag=false;
        while(true) {
            try {
                zooKeeper.create(path, null,
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            } catch (KeeperException e) {
                flag=false;
                continue;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag=true;
            if (flag){
                break;
            }
        }
        System.out.println("Locked");
    }
    public static void unlock(){
        try {
            zooKeeper.delete(path,0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        System.out.println("UnLocked");
    }

}

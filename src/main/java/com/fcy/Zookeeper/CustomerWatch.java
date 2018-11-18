package com.fcy.Zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-11  21:42
 */
public class CustomerWatch implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected==watchedEvent.getState()){
            System.out.println("连接已建立。。。");
        }
    }
}

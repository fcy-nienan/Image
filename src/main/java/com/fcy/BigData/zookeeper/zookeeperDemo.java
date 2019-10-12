package com.fcy.BigData.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class zookeeperDemo {
    private String host="127.0.0.1";
    private int port=2181;
    private ZooKeeper zooKeeper;
    private void connect()throws IOException {
        zooKeeper=new ZooKeeper(host+":"+port,0,null);
    }
    public static void main(String args[]) throws Exception {
        zookeeperDemo demo=new zookeeperDemo();
        demo.connect();
    }
}

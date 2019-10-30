package Zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-11  21:33
 */
public class ZooDataSource {
    private static final String host="127.0.0.1";
    private static final int port=2181;
    private static final String zpath="/";
    private static ZooKeeper zooKeeper;
    private static List<Watcher> watchers=new ArrayList<>();
    static {
        try {
            init();
            zooKeeper=new ZooKeeper(host+":"+port,2000,null);
            watchers.forEach(e->{
                zooKeeper.register(e);
            });
        } catch (IOException e) {
            System.out.println("Zookeeper连接失败..."+e.getMessage());
        }
    }
    private static void init(){
        watchers.add(new CustomerWatch());
    }
    public static void main(String[] args) throws InterruptedException {
        createNodeSync();
        createNodeSync();
    }
    public static void disZnode(){
        List<String> childs;
        if (zooKeeper!=null){
            try {
                childs=zooKeeper.getChildren(zpath,false);
                childs.forEach(e->{
                    System.out.println(e);
                });
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void createNodeSync(){
        try {
            System.out.println("创建临时节点。。。");
            zooKeeper.create("/tmp","838502".getBytes()
                    ,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            System.out.println("创建临时顺序节点。。。");
            zooKeeper.create("/tmpseq","838502".getBytes()
                    ,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void createNodeASync(){
        zooKeeper.create("/async1","838502".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,new CreateNodeCallBack(),"test");
    }
    public static void otherCommand(){
        System.out.println(zooKeeper.getSessionId());
        System.out.println(zooKeeper.getSessionPasswd());
    }
}

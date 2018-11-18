package com.fcy.Zookeeper;

import org.apache.zookeeper.AsyncCallback;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-11  22:41
 */
public class CreateNodeCallBack implements AsyncCallback.StringCallback {
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println(rc);
        System.out.println(path);
        System.out.println(ctx);
        System.out.println(name);
    }
}

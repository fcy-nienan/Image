package com.fcy.Api.Zookeeper.DisLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-15  0:57
 */
public class DisLock implements Lock {
    private ZooDataSource dataSource;
    private static final String path="/lock";
    @Override
    public void lock() {

    }
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }
    @Override
    public boolean tryLock() {
        return false;
    }
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
    @Override
    public void unlock() {

    }
    @Override
    public Condition newCondition() {
        return null;
    }
}

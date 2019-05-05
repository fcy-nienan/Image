package com.fcy.Java.JDBC;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-04-11  22:59
 */
@Slf4j
public class BillionCount {
    static int totalData=600000000;
    static int count=1000;
    static int threadCount=40;
    static int times=totalData/count/threadCount;
    static int total=0;
    public static void main(String[] args) {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(50,80,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        for(int i=0;i<threadCount;i++){
            executor.submit(()->{
                start();
            });
        }
        executor.submit(()->{
            while (true) {
                System.out.print("\b\b\b\b\b\b\b\b\b" + total);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.shutdown();
    }
    public synchronized static void addTotal(int count){
        total+=count;
    }
    public static void start(){
        for(int i=0;i<times;i++){
            insertData(count);
            addTotal(count);
        }
    }
    public static void insertData(int count){
        String sql="insert into ttt values(?)";
        Connection connection=ConnectionSource.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            for(int i=0;i<count;i++){
                statement.setInt(1,i);
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
            log.error("insert data error!");
        }finally{
            try {
                connection.close();
            } catch (SQLException e) {
                connection=null;
            }
        }
    }
}

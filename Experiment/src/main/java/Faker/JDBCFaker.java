package Faker;

import Middle.MysqlUtil;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * Author:fcy
 * Date:2020/4/4 11:51
 * 数据库造数据
 */
@Slf4j
public class JDBCFaker {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(40,100,0,TimeUnit.SECONDS,new ArrayBlockingQueue(20));
        for (int i=0;i<1;i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    BatchInsert insert = new BatchInsert(1000000, 50000);
                    try {
                        insert.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
    }
}

package Faker;

import CommonUtil.IOUtil;
import Middle.MysqlUtil;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;
import java.util.RandomAccess;
import java.util.concurrent.TimeUnit;

/*
 * Author:fcy
 * Date:2020/4/4 16:30
 */
@Slf4j
public class BatchInsert {
    private long count;//数据量
    private long commitThreshold;
    private long fieldsNumber=10;//字段数+id=11
    private boolean showSql=true;
    private String tableName="fcy.batch";
    public BatchInsert(long count,long commitThreshold){
        this.count=count;
        this.commitThreshold=commitThreshold;
    }
    public void start() throws Exception {
        createTable();
        StopWatch watch=new StopWatch();
        watch.start();
        batchInsert();
        watch.stop();
        Long time = watch.getTime(TimeUnit.SECONDS);
        System.out.printf("count : %s -- commitThreshold : %s -- time : %s \r\n",count,commitThreshold,time);
        IOUtil.appendToFile(System.getProperty("user.home")+File.separator+"result.txt",
                (count+","+commitThreshold+","+time+"\r\n").getBytes());
    }


    public void createTable() throws SQLException {
        if (existTable(tableName)){
            log.info(tableName+"表已存在!");
            return;
        }
        log.info(tableName+"表不存在--开始新建表!");
        Connection connection= MysqlUtil.connection();
        PreparedStatement preparedStatement = connection.prepareStatement(getCreateSql(tableName, fieldsNumber));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public void batchInsert() throws Exception {
        Connection connection= MysqlUtil.connection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=connection.prepareStatement(getInsertSql(tableName,fieldsNumber));
        for (int i=1;i<=count;i++){
            for (int j=1;j<=fieldsNumber;j++){
                preparedStatement.setString(j,getValue());
            }
            preparedStatement.addBatch();
            if (i%commitThreshold==0){
                log.info("start commit batch count:"+i);
                preparedStatement.executeBatch();
                connection.commit();
                log.info("end commit batch count:"+i);
                preparedStatement.clearBatch();
                preparedStatement.clearParameters();
            }
        }
        preparedStatement.executeBatch();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }
    private Faker faker=new Faker(Locale.CHINA);
    private int circle=10;
    private Random random=new Random();

    public String getInsertSql(String tableName,long columns){
        StringBuilder builder=new StringBuilder();
        builder.append("insert into "+tableName+" values(0,");
        for (int i=0;i<columns;i++){
            builder.append("?,");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append(")");
        showSql(builder);
        return builder.toString();
    }

    public String getCreateSql(String tableName,long fields){
        StringBuilder builder=new StringBuilder();
        builder.append("create table "+tableName+" ( id int primary key auto_increment,");
        for (int i=0;i<fields;i++){
            builder.append("fields"+i+" varchar(255) default '',");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append(")");
        showSql(builder);
        return builder.toString();
    }

    public String getValue(){
        circle=random.nextInt(6);
        switch (circle){
            case 0:{
                return faker.ancient().hero();
            }
            case 1:{
                return faker.name().name();
            }
            case 2:{
                return faker.address().fullAddress();
            }
            case 3:{
                return faker.cat().name();
            }
            case 4:{
                return faker.stock().nsdqSymbol();
            }
            case 5:{
                return faker.company().name();
            }
            default:{
                return faker.beer().name();
            }
        }
    }
    private void showSql(StringBuilder builder){
        if (showSql){
            System.out.println(builder.toString());
        }
    }
    private boolean existTable(String tableName){
        Connection connection=MysqlUtil.connection();
        boolean exists=false;
        try {
            ResultSet tables = connection.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"});
            exists = tables.next();
        } catch (SQLException e) {
            exists=false;
            e.printStackTrace();
        }
        return exists;
    }

}

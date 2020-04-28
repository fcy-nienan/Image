package Advanced.JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-08  12:48
 */
public class DataSource {
    private ThreadLocal<Connection> threadLocal=ThreadLocal.withInitial(DataSource::create);
    private Properties pro;
    private static String username;
    private static String password;
    private static String url;
    private static String defaultFileName="Config.properties";
    private String driverName;
    private String database;
    private String host;
    private int port=3306;
    private String defaultParam="useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false";
    private boolean isAddParam=true;
    private String fileName;
    public String toString(){
        return this.url;
    }
    public DataSource(final boolean flag,String name){
        this.isAddParam=flag;
        this.fileName=name;
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public DataSource(){
        this(true,defaultFileName);
    }

    private void loadFile(String name) throws IOException {
        pro=new Properties();
        InputStream is =getClass().getResourceAsStream("/"+name);
        try {
            pro.load(is);
        } catch (IOException e) {
            throw new IOException("文件不存在!");
        }
    }
    private void loadData() throws IOException {
        loadFile(fileName);
        Enumeration em=pro.propertyNames();
        while(em.hasMoreElements()){
            String key= (String) em.nextElement();
            assign(key);
        }
        constructUrl();
    }
    private void constructUrl(){
        StringBuilder baseUrl=new StringBuilder("jdbc:mysql://")
                .append(host)
                .append(":")
                .append(port)
                .append("/")
                .append(database);
        if(isAddParam) {
            this.url=baseUrl.append("?").append(defaultParam).toString();
        }else{
            this.url= baseUrl.toString();
        }
    }
    private void assign(String key){
        switch (key){
            case "username":{
                this.username=pro.getProperty(key);
                checkNotNull(this.username);
                break;
            }
            case "password":{
                this.password=pro.getProperty(key);
                checkNotNull(this.password);
                break;
            }
            case "driver-name":{
                this.driverName=pro.getProperty(key);
                checkNotNull(this.driverName);
                break;
            }
            case "database":{
                this.database=pro.getProperty(key);
                checkNotNull(this.database);
                break;
            }
            case "host":{
                this.host=pro.getProperty(key);
                checkNotNull(this.host);
                break;
            }
            default:{
                throw new IllegalArgumentException("Invalid Properties---"+key);
            }
        }
    }
    private void checkNotNull(Object o){
        if(o==null||o.equals("")){
            throw new NullPointerException(o+"Value must not be Null!");
        }
    }
    public Connection getConnection(){
        return threadLocal.get();
    }
    private static Connection create(){
        try {
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            return null;
        }
    }
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(300,400,60, TimeUnit.SECONDS,new ArrayBlockingQueue(10));
        for (int i=0;i<1;i++){
            executor.submit(new Runnable() {
                @Override
                public void run(){
                    try {
                        DataSource dataSource = new DataSource();
                        Connection connection = dataSource.getConnection();
                        connection.setAutoCommit(false);
                        String sql = "insert into user values(0,?,?)";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        for(int i=0;i<10000;i++) {
                            statement.setString(1,"username:"+i);
                            statement.setString(2,"password:"+i);
                            int i1 = statement.executeUpdate();
                            System.out.printf("insert %d rows!\r\n",i1);
                        }
                        connection.commit();
                        System.out.println(Thread.currentThread().getName()+":execute:"+1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();

    }
}

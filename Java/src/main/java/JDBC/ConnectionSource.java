package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-04-11  23:03
 */
public class ConnectionSource {
    public static Connection getConnection(){
        String host="127.0.0.1";
        String username="root";
        String password="838502";
        String database="sso";
        String drivername="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://"+host+":3306"+"/"+database+"?"+"useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false";
        Connection connection=null;
        try{
            Class.forName(drivername);
            connection=DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}

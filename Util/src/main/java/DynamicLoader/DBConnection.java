package DynamicLoader;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Slf4j
public class DBConnection {
    private static String url;
    private static String username;
    private static String password;
    private static String driverName;
    private ThreadLocal<Connection> connectionThreadLocal=ThreadLocal.withInitial(DBConnection::create);
    private static void config(){

    }
    private static Connection create (){
        Connection connection=null;
        try{
            Class.forName(driverName);
            connection=DriverManager.getConnection(url,username,password);
        } catch(Exception e) {
            e.printStackTrace();
            log.error("get connection failed!"+e);
        }
        return connection;
    }
}

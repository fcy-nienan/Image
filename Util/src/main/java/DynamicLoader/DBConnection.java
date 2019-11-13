package DynamicLoader;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Slf4j
public class DBConnection {
    private static String configPath;
    private static DBConfig dbConfig=DBConfig.getInstance(configPath);
    private static ThreadLocal<Connection> connectionThreadLocal=ThreadLocal.withInitial(DBConnection::create);
    public static void config(String path) throws IOException {
        configPath=path;
        dbConfig=DBConfig.getInstance(configPath);
        dbConfig.load();
        dbConfig.start();
    }
    public static Connection getConnection(){
        return connectionThreadLocal.get();
    }
    private static Connection create (){
        Connection connection=null;
        try{
            Class.forName(dbConfig.getDriverName());
            connection=DriverManager.getConnection(
                    dbConfig.getUrl(),
                    dbConfig.getUsername(),
                    dbConfig.getPassword());
        } catch(Exception e) {
            e.printStackTrace();
            log.error("get connection failed!"+e);
        }
        return connection;
    }
}

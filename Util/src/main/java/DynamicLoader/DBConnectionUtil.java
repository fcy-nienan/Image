package DynamicLoader;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Slf4j
public class DBConnectionUtil {
    private String configPath;
    private DBConfig dbConfig;
    public void config(String path) throws IOException {
        configPath=path;
        dbConfig=new DBConfig(path);
        dbConfig.load();
    }
    public void configAndStart(String configPath) throws IOException {
        config(configPath);
        start();
    }
    public void start(){
        dbConfig.start();
    }
    public Connection getConnection(){
        return connectionThreadLocal.get();
    }
    private ThreadLocal<Connection> connectionThreadLocal=new ThreadLocal<Connection>(){
        @Override
        protected Connection initialValue () {
            return create();
        }
    };
    private Connection create (){
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

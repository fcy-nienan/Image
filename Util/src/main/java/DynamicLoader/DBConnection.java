package DynamicLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String url;
    private String username;
    private String password;
    private String dbName;
    private String driverName;
    private String host;
    private int port;
    private String param;
    static
    {
        database="fynovel";
        drivername="com.mysql.jdbc.Driver";
//        String host="120.79.158.25";
        String host="127.0.0.1";
        dburl="jdbc:mysql://"+host+":3306/"+database+"?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false";
        userName="root";
        userPwd="838502";
        try{
            Class.forName(drivername);
            dbconn=DriverManager.getConnection(dburl,userName,userPwd);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.print("fail");
        }
    }
    public static Connection getConnection(){
        Connection connection=null;
        try{
            connection= DriverManager.getConnection(dburl,userName,userPwd);
        }catch (Exception e){
            try {
                connection=DriverManager.getConnection(dburl,userName,userPwd);
            } catch (SQLException e1) {
                try {
                    connection=DriverManager.getConnection(dburl,userName,userPwd);
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return connection;
    }
}

package Middle;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/*
 * Author:fcy
 * Date:2020/4/4 14:50
 */
@Slf4j
public class MysqlUtil {
    private static final String MYSQL_URL="mysql.url";
    private static final String MYSQL_USERNAME="mysql.username";
    private static final String MYSQL_PASSWORD="mysql.password";
    private static final String MYSQL_DRIVER="mysql.driver";
    private static String url;
    private static String username;
    private static String password;
    private static String driver;
    static {
        url=GetProperties.get(MYSQL_URL);
        username=GetProperties.get(MYSQL_USERNAME);
        password=GetProperties.get(MYSQL_PASSWORD);
        driver=GetProperties.get(MYSQL_DRIVER);
        log.info(url+":"+username+":"+password+":"+driver);
    }
    public static Connection connection()  {
        Connection con=null;
        while (true){
            try {
                if (((con=DriverManager.getConnection(url,username,password))!=null)) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}

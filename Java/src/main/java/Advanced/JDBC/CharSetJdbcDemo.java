package Advanced.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CharSetJdbcDemo {
    private static Logger logger = Logger.getLogger(CharSetJdbcDemo.class.getName());

    public static void main(String args[]) throws Exception {
        insert();
    }
    public static void query()throws Exception {
        Connection connection = ConnectionSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from one");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            System.out.println("--"+id+"--"+name);
        }
    }
    public static void insert() throws Exception {
        Connection connection = ConnectionSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into one values(?,?)");
        statement.setString(1,"1");
        statement.setString(2,new String("聂楠".getBytes("gbk")));
        statement.executeUpdate();
    }
}

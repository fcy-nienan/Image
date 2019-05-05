package com.fcy.Java.ClassLoader;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-06  0:24
 */
public class IsFollowParentDelegation {
    public static void main(String[] args) throws SQLException {
        System.out.println(java.sql.Driver.class.getClassLoader());
//        System.out.println(com.mysql.jdbc.Driver.class.getClassLoader());
        String url = "jdbc:mysql://localhost:3306/hdfs";
        Connection conn = java.sql.DriverManager.getConnection(url, "root", "838502");
        System.out.println(conn.getClass().getClassLoader());
//        Enumeration<Driver> driverEnum= DriverManager.getDrivers();
//        while (driverEnum.hasMoreElements()){
//            Driver driver=driverEnum.nextElement();
//            System.out.printf("%s : %s \r\n",driver.getClass().getName(),driver.getClass().getClassLoader());
//        }
//        System.out.printf("%s : %s \r\n","java.sql.Driver",java.sql.Driver.class.getClassLoader());
//        System.out.println(conn.getClass().getName());
//        System.out.println(conn.getClass().getClassLoader());
//        System.out.println(System.getProperty("jdbc.drivers"));
//        System.out.printf("java.ext.dir:%s \r\n",System.getProperty("java.ext.dir"));
//        JDBC4Connection connection;
//        ClassLoader classLoader;
////        Class.forName("test");
    }
}

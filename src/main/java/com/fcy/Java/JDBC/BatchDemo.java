package com.fcy.Java.JDBC;

import java.sql.*;

public class BatchDemo {
    public static void main(String[] args) {
    }
    public static Connection init(){
        String host="127.0.0.1";
        String username="root";
        String password="838502";
        String database="sso";
        String drivername="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://"+host+":3306"+"/"+database;
        Connection connection=null;
        try{
            Class.forName(drivername);
            connection=DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public void addBatch(){
        String sql="insert into table values(?,?)";
        PreparedStatement statement;
        Connection connection=init();
        try {
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            for(int i=0;i<10;i++){
                statement.setString(1,"username");
                statement.setString(2,"password");
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

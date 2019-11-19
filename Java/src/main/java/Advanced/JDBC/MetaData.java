package Advanced.JDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-08  12:47
 */
public class MetaData {
    public static void main(String args[]) throws Exception {
        testMeta();
    }
    public static void testMeta() throws Exception {
        DataSource dataSource=new DataSource();
        Connection connection=dataSource.getConnection();
        DatabaseMetaData metaData= connection.getMetaData();
        disMetaData(metaData);
    }
    public static void disMetaData(DatabaseMetaData dbmd)throws Exception{
        // 元数据信息
        println("驱动的名字：" + dbmd.getDriverName());
        println("驱动的版本号:" + dbmd.getDriverVersion());
        println("数据库产品名字:" + dbmd.getDatabaseProductName());
        println("数据库产品版本号：" + dbmd.getDatabaseProductVersion());
        println("数据库主版本号:" + dbmd.getDatabaseMajorVersion());
        println("数据库次版本号:" + dbmd.getDatabaseMinorVersion());

        println("JDBC主版本号:" + dbmd.getJDBCMajorVersion());
        println("JDBC次版本号:" + dbmd.getJDBCMinorVersion());
        // 元数据获得的数据库名字结果集
        ResultSet dbResultSet = dbmd.getCatalogs();
        // 打印所有数据库的名字
        System.out.println("所有数据库:");
        while (dbResultSet.next()) {
            println(dbResultSet.getString(1));
        }

    }
    public static void println(Object o){
        System.out.println(o);
    }
    public static void print(Object o){
        System.out.print(o);
    }
}

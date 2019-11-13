package Attack;

import DynamicLoader.DBConnectionUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@ToString
@Slf4j
public class DBSource implements CommandSource {
    @Setter
    private String path;
    private DBConnectionUtil connectionUtil=new DBConnectionUtil();
    public DBSource(){
        this(System.getProperty("user.home")+ File.separator+"db.ini");
    }

    public static void main (String[] args) {
        for (String command : new DBSource("D:\\command").getCommands()) {
            System.out.println(command);
        }
    }
    public DBSource(String path){
        this.path=path;
        try {
            connectionUtil.configAndStart(path);
        } catch (IOException e) {
            log.error("initialization database config failed!");
        }
    }
    @Override
    public List<String> getCommands () {
        return selectCommandsAndUpdateCount();
    }
    private List<String> selectCommandsAndUpdateCount(){
        List<String> commandList=new ArrayList<>();
        StringBuilder builder=new StringBuilder();
        Connection connection = connectionUtil.getConnection();
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        try {
            preparedStatement= connection.prepareStatement("select * from command where count=0");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                builder.append(id).append(",");
                String command = resultSet.getString("name");
                commandList.add(command);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (builder.length()!=0) {
            builder.deleteCharAt(builder.length() - 1);
            updateCount(builder.toString());
        }
        return commandList;
    }
    private void updateCount(String ids){
        Connection connection=connectionUtil.getConnection();
        PreparedStatement preparedStatement;
        try{
            preparedStatement=connection.prepareStatement("update command set count=count+1 where id in(?)");
            preparedStatement.setString(1,ids);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

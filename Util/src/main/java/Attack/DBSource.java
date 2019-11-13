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
@Data
@NoArgsConstructor
@ToString
@Slf4j
public class DBSource implements CommandSource {
    @Setter
    private String path;
    private DBConnectionUtil connectionUtil=new DBConnectionUtil();
    public DBSource(){
        this(System.getProperty("user.home")+ File.separator+"db.ini");
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
        return null;
    }

    public static void main (String[] args) {

    }
    private List<String> selectCommands(){
        List<String> commandList=new ArrayList<>();
        Connection connection = connectionUtil.getConnection();
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        try {
            preparedStatement= connection.prepareStatement("select * from command");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String command = resultSet.getString("command");
                commandList.add(command);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandList;
    }
}

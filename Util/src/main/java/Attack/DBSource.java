package Attack;

import DynamicLoader.DBConnection;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Connection;
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
    @Override
    public List<String> getCommands () {
        return null;
    }
    private List<String> selectCommands(){
        List<String> command=new ArrayList<>();
        try {
            DBConnection.config(path);
        } catch (IOException e) {
            log.error("initialization database config failed!"+e);
            return null;
        }
        Connection connection = DBConnection.getConnection();
        connection.prepareStatement("select * from fcy");
    }
}

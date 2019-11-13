package Attack;

import java.util.List;

public class DBSource implements CommandSource {
    private String url;
    private String username;
    private String password;
    private String dbName;
    private String driverName;
    
    @Override
    public List<String> getCommands () {
        return null;
    }
}

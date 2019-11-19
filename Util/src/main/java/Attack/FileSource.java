package Attack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileSource implements CommandSource{
    private String scriptPath;
    private String fileCharset;
    private static Logger logger = Logger.getLogger(FileSource.class.getName());
    public FileSource scriptPath(String path){
        this.scriptPath=path;
        return this;
    }
    public FileSource fileCharset(String charset){
        this.fileCharset=charset;
        return this;
    }
    @Override
    public List<String> getCommands() {
        List<String> commands=new ArrayList<>();
        File f=new File(scriptPath);
        if (!f.exists()){
            logger.info("----------file is not exist!"+scriptPath);
            try {
                f.createNewFile();
                logger.warning("----------auto create new file:"+scriptPath);
            } catch (IOException e) {
                e.printStackTrace();
                logger.warning("----------auto create new file failed!"+scriptPath);
            }
            return commands;
        }
        if (f.length()==0){
            logger.info("----------file is empty!"+scriptPath);
            return commands;
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), fileCharset));
            String s = null;
            while ((s = reader.readLine()) != null) {
                commands.add(s);
            }
            reader.close();
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write("".getBytes());
            outputStream.close();
        }catch (IOException e){
            logger.warning("----------io exception while reading file command!"+e);
        }
        return commands;
    }
}

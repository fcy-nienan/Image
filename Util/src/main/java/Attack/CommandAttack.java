package Attack;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class CommandAttack implements Runnable{
    private Logger logger = Logger.getLogger(CommandAttack.class.getSimpleName());
    private static final String configPath="attack.config";
    private static final String fileName="command";
    private static final String fileCharset="utf-8";

    private String home=System.getProperty("user.home");
    private String logDir=home+File.separator+"attlog"+File.separator;
    private boolean configStatus=false;
    private long configLastModified=0;

    private Long timeout=5000l;

    private CommandSource commandSource;
    private void ensureLogPath(){
        logDir=home+File.separator+"attlog"+File.separator;
        File f=new File(logDir);
        if (!f.exists()){
            f.mkdirs();
        }
    }
    private void updateCommandSourceConfig(){
        String scriptPath = home + File.separator + fileName;
        ((FileSource) this.commandSource)
                .fileCharset(fileCharset)
                .scriptPath(scriptPath);
    }
    public CommandAttack(){
        this.commandSource=new FileSource();
        updateCommandSourceConfig();
    }
    public static void main(String args[]) throws Exception {
        new Thread(new CommandAttack()).start();
    }
    private void checkConfig(){
        String path=System.getProperty("java.io.tmpdir")+File.separator+configPath;
        File file=new File(path);
        Properties properties=new Properties();
        try {
            properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(file),fileCharset)));
        } catch (IOException e) {
            logger.warning("----------no config file!--"+path+e.getMessage());
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            logger.warning("----------create new config file:"+path);
            return;
        }
        long nowModified=file.lastModified();
        if (nowModified==configLastModified){
            Date now=new Date(nowModified);
            Date last=new Date(configLastModified);
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            logger.warning("----------not modified!now:"+format.format(now)+"--last:"+format.format(last));
            return;
        }
        configLastModified=nowModified;

        String time = properties.getProperty("timeout");
        if (notEmptyAndChange(time,timeout.toString())){
            try{
                long t=Long.parseLong(time);
                logger.info("----------timeout status changed! new value:"+time+" and old value:"+timeout);
                timeout=t;
                updateStatus();
            }catch (NumberFormatException ex){
                logger.warning("----------timeout is not a valid number!"+time);
            }
        }


        String h = properties.getProperty("home");
        if (notEmptyAndChange(h,home)){
            File f=new File(h);
            if (!f.exists()){
                f.mkdirs();
            }
            logger.info("----------home status changed! new value:"+h+" and old value:"+home);
            home=h;
            updateStatus();
        }
        ensureLogPath();
        updateCommandSourceConfig();
        disNowConfig();
    }
    private void disNowConfig(){
        if (configStatus){
            logger.info("----------now config----------");
            logger.info("timeout:"+timeout);
            logger.info("home:"+home);
            resetStatus();
        }else{
            logger.info("----------config not modified!----------");
        }
    }
    private void updateStatus(){
        if (!configStatus) {
            configStatus = true;
        }
    }
    private void resetStatus(){
        if (configStatus) {
            configStatus = false;
        }
    }
    private boolean notEmptyAndChange(String s,String compare){
        return s!=null&&!s.equals("")&&!s.equals(compare);
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(timeout);
                checkConfig();
                execFileCommand();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void execFileCommand() throws IOException, InterruptedException {
        List<String> commandList = commandSource.getCommands();
        for (String command : commandList) {
            String l = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(System.currentTimeMillis()));
            File file = new File(logDir + command
                    .replaceAll("\\s", "")
                    .replaceAll("\\/", "")
                    .replaceAll("\\\\", "") + l);
            String[] commands = command.split(" ");
            logger.info("----------exec command: " + command + "----------");
            ProcessBuilder builder = new ProcessBuilder();
            Process process = builder.command(commands)
                    .redirectErrorStream(true)
                    .redirectOutput(file)
                    .start();
            process.waitFor();
            process.destroy();
        }
    }
}

package Attack;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class CommandAttack {
    private static Logger logger = Logger.getLogger(CommandAttack.class.getName());
    private static final String home=System.getProperty("user.home");
    private static final String configPath="attack.config";
    private static boolean configStatus=false;
    private static String fileName="command";
    private static String fileCharset="utf-8";
    private static Long timeout=5000l;
    private static long configLastModified=0;
    public static void main(String args[]) throws Exception {
        new Thread(new commandRunnable()).start();
    }
    private static void checkParam(){
        String path=home+File.separator+configPath;
        File file=new File(path);
        long nowModified=file.lastModified();
        if (nowModified==configLastModified){
            return;
        }
        configLastModified=nowModified;
        Properties properties=new Properties();
        try {
            properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8")));
        } catch (IOException e) {
            logger.warning("no config file!--"+path+e.getMessage());
            throw new RuntimeException("can not run this program!");
        }
        String name = properties.getProperty("fileName");
        if (notEmptyAndChange(name,fileName)){
            fileName=name;
            updateStatus();
        }
        String charset = properties.getProperty("fileCharset");
        if (notEmptyAndChange(charset,fileCharset)){
            fileCharset=charset;
            updateStatus();
        }
        String time = properties.getProperty("timeout");
        if (notEmptyAndChange(time,timeout.toString())){
            try {
                long t = Long.parseLong(time);
                timeout=t;
                updateStatus();
            }catch (NumberFormatException e){
                logger.warning("timeout is not a valid number!"+time);
            }
        }
        if (configStatus){
            logger.info("----------new config----------");
            System.out.println("timeout:"+timeout);
            System.out.println("charset:"+fileCharset);
            System.out.println("fileName:"+fileName);
            resetStatus();
        }
    }
    private static void updateStatus(){
        if (!configStatus) {
            configStatus = true;
        }
    }
    private static void resetStatus(){
        if (configStatus) {
            configStatus = false;
        }
    }
    private static boolean notEmptyAndChange(String s,String compare){
        return s!=null&&!s.equals("")&&!s.equals(compare);
    }
    public static class commandRunnable implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    checkParam();
                    Thread.sleep(timeout);
                    execFileCommand();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void execFileCommand() throws IOException, InterruptedException {
        String scriptPath=home+File.separator+fileName;
        File f=new File(scriptPath);
        if (!f.exists()){
            logger.info("file is not exist!"+scriptPath);
            return;
        }
        if (f.length()==0){
            logger.info("file is empty!"+scriptPath);
            return;
        }
        if (f.exists()) {
            List<String> commandList=new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f),fileCharset));
            String s=null;
            while ((s=reader.readLine())!=null){
                commandList.add(s);
            }
            reader.close();
            for (String command:commandList) {
                logger.info("----------exec command "+command+"----------");
                Process process = Runtime.getRuntime().exec(command);

                System.out.println("----------stdout output----------");
//                displayStream(process.getInputStream());

                System.out.println("----------error output----------");
//                displayStream(process.getErrorStream());

                process.waitFor();
                process.destroy();
            }
        }
    }
    public static void displayStream(InputStream inputStream) throws IOException {
        BufferedReader error=new BufferedReader(new InputStreamReader(inputStream));
        String s2=null;
        while ((s2=error.readLine())!=null){
            System.out.println(s2);
        }
        error.close();
    }
}

package DynamicLoader;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
/*
* provide two method
* load and start
* load will load the configuration into memory
* start will start a thread and update the config regularly
* */
@Slf4j
public class DBConfig {
    @Config
    private volatile String url;
    @Config
    private volatile String username;
    @Config
    private volatile String password;
    @Config
    private volatile String driverName;
    private boolean started;
    private String path;
    private Thread thread;
    public static void main (String[] args) {
        DBConfig config=new DBConfig();
        config.load("");
    }
    private File preCheck(String path){
        File file=new File(path);
        if (!file.exists()){
            log.error("file is not exists"+path);
            return null;
        }
        if (file.isDirectory()){
            log.error("file is a directory"+path);
            return null;
        }
        return file;
    }
    public synchronized void start(){
        if (started){
            log.warn("background thread has started!");
            return;
        }
        thread=new Thread(new updateThread());
        thread.start();
    }
    public synchronized void stop(){
        if (!started){
            log.warn("background thread has not started!");
            return;
        }
        thread.interrupt();
    }
    public void load(String path) throws IOException {
        File file = preCheck(path);
        Properties properties=new Properties();
        properties.load(new FileInputStream(file));
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Config annotation = declaredField.getAnnotation(Config.class);
            if (annotation==null){
                log.warn(declaredField.getName()+" no have config");
                continue;
            }
            declaredField.setAccessible(true);
            String name=declaredField.getName();
            String property = properties.getProperty(name);
            if (property!=null){
                try {
                    declaredField.set(this,property);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class updateThread implements Runnable{
        @Setter
        private long timeout;
        @Override
        public void run () {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(timeout);
                    load(path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

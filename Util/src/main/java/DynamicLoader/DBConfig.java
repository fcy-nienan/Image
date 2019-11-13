package DynamicLoader;

import lombok.Getter;
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
    @Getter
    private volatile String url;
    @Config
    @Getter
    private volatile String username;
    @Config
    @Getter
    private volatile String password;
    @Config
    @Getter
    private volatile String driverName;
    private boolean started;
    private String path;
    private Thread thread;
    private long lastModified;
    public DBConfig(String path){
        this.path=path;
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
        started=true;
    }
    public synchronized void stop(){
        if (!started){
            log.warn("background thread has not started!");
            return;
        }
        thread.interrupt();
        started=false;
    }
    private boolean needConfig(File f){
        long n=f.lastModified();
        if (n==lastModified){
            log.info("config file is not changed , don't need modify!");
            return false;
        }
        lastModified=n;
        return true;
    }
    public void load() throws IOException {
        load(path);
    }
    public void load(String path) throws IOException {
        File file = preCheck(path);
        if (!needConfig(file)){
            return;
        }
        Properties properties=new Properties();
        properties.load(new FileInputStream(file));
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Config annotation = declaredField.getAnnotation(Config.class);
            if (annotation==null){
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
        displayConfig();
    }
    private void displayConfig(){
        log.info("----------now db config----------");
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getAnnotation(Config.class)!=null){
                declaredField.setAccessible(true);
                try {
                    log.info("----------"+
                            declaredField.getName()+":"+
                            declaredField.get(this)+
                            "----------");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class updateThread implements Runnable{
        @Setter
        private long timeout=2000;
        @Override
        public void run () {
            log.info("--------start background update config thread--------");
            while (!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(timeout);
                    load(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (InterruptedException e) {
                    break;
                }
            }
            log.info("--------stopped background update config thread--------");
        }
    }
}

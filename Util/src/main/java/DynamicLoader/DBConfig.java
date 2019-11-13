package DynamicLoader;

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
    private String url;
    private String username;
    private String password;
    private String driverName;

    public static void main (String[] args) {
        Field[] declaredFields = DBConfig.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
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
    public void load(String path) throws IOException {
        File file = preCheck(path);
        Properties properties=new Properties();
        properties.load(new FileInputStream(file));
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
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
    static class updateThread implements Runnable{
        @Override
        public void run () {

        }
    }
}

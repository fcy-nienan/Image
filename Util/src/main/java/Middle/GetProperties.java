package Middle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/*
 * Author:fcy
 * Date:2020/4/4 14:53
 */
public class GetProperties {
    private static String path=System.getProperty("user.home");
    private static String fileName="fcy.properties";
    private static Properties properties;
    static {
        properties=new Properties();
        try {
            properties.load(new FileReader(path+ File.separator+fileName));
        } catch (IOException e) {
            throw new RuntimeException("用户目录下没有相应的配置文件:"+path+fileName+"!",e);
        }
    }
    public static String get(String key){
        return properties.getProperty(key);
    }
}

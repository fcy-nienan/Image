package Log;

import lombok.extern.slf4j.Slf4j;

/**
 * @descripiton:
 * 关于日志
 * 日志输出到哪里
 * 日志输出的格式
 * 输出哪个级别的日志
 *
 *
 * @author: fcy
 * @date: 2019-03-09  18:27
 */
@Slf4j
public class FcyLog {
    private int level=0;
    private Class clazz;
    public static FcyLog getLogger(Class clazz,Level level){
        return new FcyLog(clazz,level.level);
    }
    private FcyLog(Class clazz,int level){
        this.clazz=clazz;
        this.level=level;
    }
    public void info(String msg){
        if (level>= Level.INFO.level){
            System.out.println(clazz.getSimpleName()+":"+msg);
        }
    }
    public void debug(String msg){
        if (level>= Level.DEBUG.level){
            System.out.println(clazz.getSimpleName()+":"+msg);
        }
    }
    public void error(String msg){
        if (level>= Level.ERROR.level){
            System.out.println(clazz.getSimpleName()+":"+msg);
        }
    }
    public enum Level{
        INFO(1,"info"),DEBUG(2,"debug"),ERROR(3,"error");
        private int level;
        private String str;
        Level(int level,String str){
            this.level=level;
            this.str=str;
        }

    }
}

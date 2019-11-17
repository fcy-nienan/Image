package DynamicLoader;

import com.google.common.io.Files;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class ClassLoaderTask implements Runnable {
    private FcyClassLoader classLoader;
    private long timeout=0;
    private volatile String home;
    private boolean started;
    private Thread thread;
    public ClassLoaderTask(String home){
        this.home=home;
    }
    public synchronized void start(){
        if (started){
            return;
        }
        startAction();
        started=true;
    }
    public synchronized void stop(){
        if (!started){
            return;
        }
        log("stop class loader task!");
        thread.interrupt();
        started=false;
    }
    private void log(String msg){
        log.info("----------"+msg+"----------");
    }
    private void log(String msg,String object){log.info("----------"+msg+"----------",object);}
    private void startAction(){
        log("start class loader task!");
        thread=new Thread(this,"class loader task");
        thread.start();
    }
    private void compileFile(String src){
        log("start compile dir all file {}!",src);
        File[] files=new File(src).listFiles();
        for (File file : files) {
            if (file.isFile()&&file.getName().endsWith(".java")){
                try {
                    CompileFile.compile(file.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    log("compile file failed :"+file.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
    }
    private void executeAction(List<Class<?>> list){
        log("start execute all method ! size {} !",String.valueOf(list.size()));
        for (Class<?> aClass : list) {
            try {
                Object o=aClass.newInstance();
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    try {
                        log("execute method:"+declaredMethod.getName());
                        declaredMethod.invoke(o);
                    } catch (Exception e) {
                        log("invoke method failed:"+declaredMethod.getName());
                        e.printStackTrace();
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    private void clearDirectory(String path){
        log("start clean {} directory",path);
        File file=new File(path);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isFile()&&(file1.getName().endsWith(".java")||file1.getName().endsWith(".class"))){
                boolean delete = file1.delete();
                if (!delete){
                    log("delete file failure!"+file1.getName());
                    file1.renameTo(new File(file1.getAbsolutePath()+"bak"));
                }else{
                    log("delete file success {} !",file1.getName());
                }
            }
        }
    }
//    need
    private List<Class<?>> loadClass(String src){
        log("start load class in {}!",src);
        List<Class<?>> list=new ArrayList<>();
        File[] files=new File(src).listFiles();
        classLoader=new FcyClassLoader(src);
        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().endsWith(".class")) {
                    try {
                        String regular = file.getName().replace(home, "").replace(".class", "");
                        Class clazz = classLoader.findClass(regular);
                        list.add(clazz);
                        log("load class {} success!", file.getName());
                    } catch (ClassNotFoundException e) {
                        log("load class failed:" + file.getName());
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        }
        return list;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                String path=home;
                compileFile(path);
                List<Class<?>> list = loadClass(path);
                executeAction(list);
//                clearDirectory(path);
                log("-------------------");
                log("-------------------");
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

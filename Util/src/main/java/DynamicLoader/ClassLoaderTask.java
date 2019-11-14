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
    private long timeout=5000;
    private String home;
    private boolean started;
    private Thread thread;
    public ClassLoaderTask(String home){
        this.home=home;
        this.classLoader=new FcyClassLoader(home);
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
    }
    private void log(String msg){
        log.info("----------"+msg+"----------");
    }
    private void startAction(){
        log("start class loader task!");
        thread=new Thread(this);
        thread.start();
    }
    private void compileFile(){
        File[] files=new File(home).listFiles();
        for (File file : files) {
            if (file.getName().endsWith("java")){
                try {
                    CompileFile.compile(file.getAbsolutePath());
                } catch (Exception e) {
                    log("compile file failed :"+file.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
    }
    private void executeAction(List<Class<?>> list){
        for (Class<?> aClass : list) {
            try {
                Object o=aClass.newInstance();
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    try {
                        declaredMethod.invoke(o);
                    } catch (InvocationTargetException e) {
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
    private void clearDirectory(){
        File file=new File(home);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.getName().endsWith(".java")||file1.getName().endsWith(".class")){
                boolean delete = file1.delete();
                if (!delete){
                    log("delete file failure!"+file1.getName());
                    file1.renameTo(new File(file1.getAbsolutePath()+"bak"));
                }
            }
        }
    }
    private List<Class<?>> loadClass(){
        List<Class<?>> list=new ArrayList<>();
        File[] files=new File(home).listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".class")){
                try {
                    Class clazz=classLoader.findClass(file.getName());
                    list.add(clazz);
                } catch (ClassNotFoundException e) {
                    log("load class failed:"+file.getName());
                    e.printStackTrace();
                    continue;
                }
            }
        }
        return list;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(timeout);
                compileFile();
                List<Class<?>> list = loadClass();
                executeAction(list);
                clearDirectory();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

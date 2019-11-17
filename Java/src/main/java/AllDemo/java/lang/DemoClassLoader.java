package AllDemo.java.lang;

import DynamicLoader.FcyClassLoader;
import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
//Each class will use its own classloader to load other classes.
//So if ClassA.class references ClassB.class
//then ClassB needs to be on the classpath of the classloader of ClassA, or its parents.
//The thread context classloader is the current classloader for the current thread.
//An object can be created from a class in ClassLoaderC and then passed to a thread owned by ClassLoaderD.
//In this case the object needs to use Thread.currentThread().getContextClassLoader() directly
//if it wants to load resources that are not available on its own classloader

/*
* 在一个线程中加载类时使用的是当前类的类加载器还是线程上下文类加载器？
* 当前类的类加载器
*
* 关于线程上下文加载器
* 首先需要记住的是双亲委托模型只代表ClassLoader类中的loadClass这一段代码，只有调用了这段代码才代表使用了双亲委托模型
* 而在加载Driver接口的时候使用的是当前类的类加载器是BootStrap加载器,该加载器是无法加载在AppSystem类加载器下的类文件的
* 所以只能通过Thread拿到另一个加载器去加载类路径，比如AppSystemClassLoader类加载器，
* 然后通过loadClass的代码向上一直委托到Bootstrap类加载器，都加载不到，然后向下委派直到AppSystemClassLoader自己去加载
* */
public class DemoClassLoader {
    public static void main (String args[]) throws InterruptedException {
        Object o;
        ClassLoader loader;
        Driver driver;
//        disPath();
//        Thread.sleep(3000000);
        dis();
    }
    static class t implements Runnable{
        @Override
        public void run () {
            System.out.println(this.getClass().getClassLoader());
        }
    }
    public static void dis(){
        Thread t=new Thread(new t());
        t.setContextClassLoader(new FcyClassLoader("D:\\classes\\"));
        t.start();
        System.out.println(File.class.getClassLoader());
    }
    public static void disPath(){
        System.out.println("----------bootstrap class loader path----------");
        URLClassPath bootstrapClassPath = Launcher.getBootstrapClassPath();
        URL[] urLs = bootstrapClassPath.getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.getPath());
        }

        System.out.println("----------extension class loader path----------");
        URLClassLoader parent = (URLClassLoader) (ClassLoader.getSystemClassLoader().getParent());
        URL[] urLs1 = parent.getURLs();
        for (URL url : urLs1) {
            System.out.println(url.getPath());
        }

        System.out.println("----------system class loader path----------");
        URLClassLoader systemClassLoader = (URLClassLoader) (ClassLoader.getSystemClassLoader());
        URL[] urLs2 = systemClassLoader.getURLs();
        for (URL url : urLs2) {
            System.out.println(url.getPath());
        }

        String paths=System.getProperty("java.ext.dirs");
        String appPaths=System.getProperty("java.class.path");
    }
    /*
    *
    *
    *               正常的三个类加载器的路径,IDE中的一些都是额外配置了的
         ----------bootstrap class loader path----------
        /D:/Code/Language/java/jre/lib/resources.jar
        /D:/Code/Language/java/jre/lib/rt.jar
        /D:/Code/Language/java/jre/lib/sunrsasign.jar
        /D:/Code/Language/java/jre/lib/jsse.jar
        /D:/Code/Language/java/jre/lib/jce.jar
        /D:/Code/Language/java/jre/lib/charsets.jar
        /D:/Code/Language/java/jre/lib/jfr.jar
        /D:/Code/Language/java/jre/classes
        ----------extension class loader path----------
        /D:/Code/Language/java/jre/lib/ext/access-bridge-64.jar
        /D:/Code/Language/java/jre/lib/ext/cldrdata.jar
        /D:/Code/Language/java/jre/lib/ext/dnsns.jar
        /D:/Code/Language/java/jre/lib/ext/jaccess.jar
        /D:/Code/Language/java/jre/lib/ext/jfxrt.jar
        /D:/Code/Language/java/jre/lib/ext/localedata.jar
        /D:/Code/Language/java/jre/lib/ext/nashorn.jar
        /D:/Code/Language/java/jre/lib/ext/sunec.jar
        /D:/Code/Language/java/jre/lib/ext/sunjce_provider.jar
        /D:/Code/Language/java/jre/lib/ext/sunmscapi.jar
        /D:/Code/Language/java/jre/lib/ext/sunpkcs11.jar
        /D:/Code/Language/java/jre/lib/ext/zipfs.jar
        ----------system class loader path----------
        /D:/
        /D:/Code/Language/java/lib/dt.jar
        /D:/Code/Language/java/lib/tools.jar
        /D:/Code/Language/java/jre/lib/rt.jar

    *
    *
    *
    *
    *
    *
    *
    *
    *
    * */
}

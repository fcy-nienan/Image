package AllDemo.java.lang;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.net.URL;
import java.net.URLClassLoader;

public class DemoClassLoader {
    public static void main (String args[]) throws InterruptedException {
        disPath();
        Thread.sleep(3000000);
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

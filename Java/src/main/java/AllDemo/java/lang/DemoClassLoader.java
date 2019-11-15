package AllDemo.java.lang;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.net.URL;

public class DemoClassLoader {
    public static void main (String args[]) {
        disPath();
        ClassLoader loader;
    }
    public static void disPath(){
        System.out.println("----------bootstrap class loader path----------");
        URLClassPath bootstrapClassPath = Launcher.getBootstrapClassPath();
        URL[] urLs = bootstrapClassPath.getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.getPath());
        }

        System.out.println("----------extension class loader path----------");
        String paths=System.getProperty("java.ext.dirs");
        String[] path=paths.split(";");
        for (String s : path) {
            System.out.println(s);
        }

        System.out.println("----------system class loader path----------");
        String appPaths=System.getProperty("java.class.path");
        String[] appPath=appPaths.split(";");
        for (String s : appPath) {
            System.out.println(s);
        }



    }
}

package com.fcy.Util.FileTransfer.Common;

import java.util.logging.Logger;

public class SystemUtil {
    private static Logger logger = Logger.getLogger(SystemUtil.class.getName());
    private static boolean linux;
    private static boolean windows;
    static {
        String osName=System.getProperty("os.name");
        if (osName.toLowerCase().contains("linux")){
            linux=true;
        }else if (osName.toLowerCase().contains("window")){
            windows=true;
        }else{

        }
    }
    public static boolean isLinux(){
        return linux;
    }
    public static boolean isWindows(){
        return windows;
    }
    public static void main(String args[]) throws Exception {

    }
}

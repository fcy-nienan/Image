package com.fcy.Util.FileTransfer.Common;

import java.util.logging.Logger;

public class BinaryUtil {
    private static Logger logger = Logger.getLogger(BinaryUtil.class.getName());
    public static boolean isLetter(char c){
        return (c>='a'&&c<='z')||(c>='A'&&c<='Z');
    }
    public static boolean isDigital(char c){
        return c>='0'&&c<='9';
    }
}

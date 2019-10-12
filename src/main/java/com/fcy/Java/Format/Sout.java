package com.fcy.Java.Format;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;
/*
* \r转义字符    回到当前行的行首
* \b转义字符    回到上一个字符
*
* */
public class Sout {
    private static Logger logger = Logger.getLogger(Sout.class.getName());

    public static void main(String args[]) throws Exception {
        System.out.print("第一次输出:"+1000);
        Thread.sleep(1000);
        System.out.print("\b\b\b");

        System.out.println();
        Thread.sleep(1000);

        System.out.print("输出:"+1000);
        Thread.sleep(1000);
        System.out.print("\rbb");
    }
}

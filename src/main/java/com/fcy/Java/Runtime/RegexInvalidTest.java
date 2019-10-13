package com.fcy.Java.Runtime;

import java.io.File;

public class RegexInvalidTest {
    public static void main(String args[]) {

        new RegexInvalidTest().execute(args[0],args[1]);
    }
    public void execute(String src,String dst){
        Process process=null;
        try{
            process=Runtime.getRuntime().exec("mv "+ src+ " " + dst);
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (process!=null){
                process.destroy();
            }
        }
    }
}

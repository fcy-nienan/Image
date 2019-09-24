package com.fcy.Java.Grammer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class TestIf {
    private static Logger logger = Logger.getLogger(TestIf.class.getName());

    public static void main(String args[]) throws Exception {
        long start,end;
        start=System.nanoTime();
        ifCount(100);
        end=System.nanoTime();
        System.out.println("cost time:"+(end-start));

        start=System.currentTimeMillis();
        ifCount(160000);
        end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));

        String s1,s2,s3;
        File file=new File("F:\\test.java");
        BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        start=System.nanoTime();
        s1=reader.readLine();
        s2= reader.readLine();
        end=System.nanoTime();
        System.out.println("cost time:"+(end-start));
        System.out.println(s1+s2);




    }
    static void ifCount(int count){
        for(int i=0;i<count;i++){
            if1();
        }
    }
    static void if1(){
        boolean flag1=false;
        boolean flag2=true;
        boolean flag3=false;
        int i=0;
        if(flag1){
            i++;
        }
        if(flag2){
            i++;
        }
        if(flag3){
            i++;
        }
    }
}

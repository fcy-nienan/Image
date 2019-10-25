package com.fcy.TimeExperiment;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class ReflectionAndNormalMethodCompare {
    private static Logger logger = Logger.getLogger(ReflectionAndNormalMethodCompare.class.getName());

    public static void main(String args[]) throws Exception {
        int count=100000;
        String s="kk";
        int a=123;
        ReflectionAndNormalMethodCompare compare=new ReflectionAndNormalMethodCompare();
        Method method=ReflectionAndNormalMethodCompare.class.getMethod("invokedNormal", String.class, int.class);

        long start=System.currentTimeMillis();
        for (int i=0;i<count;i++) {
            method.invoke(compare, s, a);
        }
        long end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));

        start=System.currentTimeMillis();
        for (int i=0;i<count;i++){
            compare.invokedNormal(s,a);
        }
        end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));
    }
    private static String invokedStatic(String s,int a){
        for (int i=0;i<a;i++){
            s=s+i;
        }
        return s;
    }
    public String invokedNormal(String s,int a){
        for (int i=0;i<a;i++){
            s+=i;
        }
        return s;
    }
}

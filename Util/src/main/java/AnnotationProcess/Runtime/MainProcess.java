package AnnotationProcess.Runtime;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class MainProcess {
    private static Logger logger = Logger.getLogger(MainProcess.class.getName());

    public static void main(String args[]) throws Exception {

    }
    public void process(Class clazz,Object o,Object... args){
        Method[] methods=clazz.getDeclaredMethods();
        for (Method method:methods){
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                if (declaredAnnotation.annotationType().getName().equals("TimeCost")){
                    long s=System.currentTimeMillis();
                    try {
                        method.invoke(o,args);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    long e=System.currentTimeMillis();
                    System.out.println("cost time:"+(e-s));
                }
            }
        }
    }
}

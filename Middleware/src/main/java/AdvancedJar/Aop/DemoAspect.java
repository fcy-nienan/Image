package AdvancedJar.Aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.logging.Logger;
@Aspect
public class DemoAspect {
    private static Logger logger = Logger.getLogger(DemoAspect.class.getName());

    public static void main(String args[]) throws Exception {
        RunClass runClass=new RunClass();
        runClass.compute();
    }
    @Before("@annotation(AdvancedJar.Aop.TimerCost)")
    public void execute()throws Throwable{
        logger.info("start execute!");
    }


}

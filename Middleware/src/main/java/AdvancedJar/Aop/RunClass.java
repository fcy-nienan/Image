package AdvancedJar.Aop;

import java.util.logging.Logger;

public class RunClass {
    private static Logger logger = Logger.getLogger(RunClass.class.getName());

    public static void main(String args[]) throws Exception {

    }
    @TimerCost
    public void compute(){

    }
}

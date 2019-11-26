package AdvancedJar.Aop;

import java.util.logging.Logger;

public class RunClass {
    private static Logger logger = Logger.getLogger(RunClass.class.getName());

    public static void main(String args[]) throws Exception {
        String ar="skdfj \r\nlsdjkfsdfj" +
                "" +
                "   ";
        ar=ar.replaceAll("\\s","");
        System.out.println(ar);
        String str="sldkfj\n" +
                "sdflsdjf\n" +
                "slkdjf\n" +
                "lksjdf\n" +
                "jlskf\n" +
                "lksjf\n";
        String s = str.replaceAll("\\s", "");
        System.out.println(s);
    }
    @TimerCost
    public void compute(){

    }
}

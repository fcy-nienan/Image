package LittleSkill.GraceFullyExit;

import sun.misc.Signal;

import java.io.IOException;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-10  21:34
 */
public class Example {
    public static void main(String[] args) throws InterruptedException, IOException {
        invokeShutdownHook();
        singal();
        while(true){
            System.out.println("kk");
            Thread.sleep(3000);
        }
    }
    private static String getOSSignalType() {
        return System.getProperties().getProperty("os.name").
                toLowerCase().startsWith("win") ? "INT" : "USR2";
    }
    private static void invokeShutdownHook() {
        Thread t = new Thread(new shutdownHook(), "ShutdownHook-Thread");
        Runtime.getRuntime().addShutdownHook(t);
    }
//    处理信号量
    private static void singal(){
        Signal sig = new Signal(getOSSignalType());
        Signal.handle(sig, (signal)->{
                System.out.println(signal.getName());
                System.out.println("gracefullyShutdown...");
                System.exit(0);
            }
        );
    }
}

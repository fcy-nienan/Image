package OtherSkill;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class SystemInfo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime run = Runtime.getRuntime();
        System.out.println("            运行时环境Runtime            ");
        System.out.println("空闲内存"+run.freeMemory()+"byte");
        System.out.println("空闲内存"+run.freeMemory()/1024+"Kb");
        System.out.println("空闲内存"+run.freeMemory()/(1024*1024)+"Mb");
        System.out.println("试图使用的最大内存:"+run.maxMemory()+"byte");
        System.out.println("最大内存"+run.maxMemory()/1024+"Kb");
        System.out.println("最大内存"+run.maxMemory()/(1024*1024)+"Mb");
        System.out.println("内存总量:"+run.totalMemory()+"字节");
        System.out.println("内存总量"+run.totalMemory()/1024+"Kb");
        System.out.println("内存总量"+run.totalMemory()/(1024*1024)+"Mb");
        System.out.println("操作系统:"+System.getProperty("os.name"));

        System.out.println("            通过运行时环境Runtime执行cmd命令           ");
        String com="cmd.exe /c tasklist | findstr \"services\"";
        Process pro = run.exec(com);
        BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        String str = "";
        while((str = br.readLine()) != null) {
            System.out.println(str);
        }
        br.close();
        pro.destroy();

        System.out.println("            输出系统属性          ");
        System.out.println(System.getProperty("APPDATA"));
        Properties systemies=System.getProperties();
        systemies.list(System.out);
        System.out.println(SystemTray.isSupported());
//        byte[] bytes=new byte[1024*1024*100];
        Thread.sleep(100000);
//        System.out.println(bytes.length);

    }
}

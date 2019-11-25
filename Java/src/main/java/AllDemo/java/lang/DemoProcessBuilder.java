package AllDemo.java.lang;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.logging.Logger;

public class DemoProcessBuilder {
    private static Logger logger = Logger.getLogger(DemoProcessBuilder.class.getName());

    public static void main(String args[]) throws Exception {
        List<String> list=new ArrayList<>();
        for (int i=0;i<args.length-1;i++){
            String c=args[i].replaceAll("\"","");
            list.add(c);
        }
        process(list,args[args.length-1]);
    }
    private static void process(List<String> commands,String charset){
        ProcessBuilder builder=new ProcessBuilder();
        for (String s:commands) {
            builder.command("/bin/bash","-c", s);
            builder.redirectErrorStream(true);
            try {
                Process start = builder.start();
                InputStream inputStream = start.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
                String msg = "";
                while ((msg = reader.readLine()) != null) {
                    System.out.println(msg);
                }
                start.waitFor();
                start.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package windwos;

import CommonUtil.IOUtil;

import java.io.*;

public class SetAllPath {

    public static void main (String args[]) throws IOException, InterruptedException {
        String allBinPath="D:\\ScheduledTask\\AllBInPath";
        BufferedReader reader= IOUtil.getBufferedReader(allBinPath);
        StringBuilder builder=new StringBuilder();
        builder.append("setx PATH \"%PATH%;");
        String msg=null;
        while ((msg=reader.readLine())!=null){
            builder.append(msg);
            builder.append(File.pathSeparator);
        }
        builder.append("\"");
        System.out.println(builder.toString());
        String cmd="cmd /c "+builder.toString();
        Process process=Runtime.getRuntime().exec(cmd);
        IOUtil.disPlayStream(process.getInputStream(),"gb2312");
        process.waitFor();
        process.destroy();
    }
}

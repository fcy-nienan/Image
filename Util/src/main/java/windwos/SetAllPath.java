package windwos;

import CommonUtil.IOUtil;

import java.io.*;

public class SetAllPath {

    public static void main (String args[]) throws IOException, InterruptedException {
        String allBinPath="D:\\ScheduledTask\\AllBInPath";
        BufferedReader reader= IOUtil.getBufferedReader(allBinPath);
        StringBuilder builder=new StringBuilder();
        String msg=null;
        while ((msg=reader.readLine())!=null){
            builder.append("cmd /c ");
            builder.append("setx PATH \"%PATH%;");
            builder.append(msg);
            builder.append(File.pathSeparator);
            builder.append("\"");
            System.out.println(builder.toString());
            Process process=Runtime.getRuntime().exec(builder.toString());
//            IOUtil.disPlayStream(process.getInputStream(),"gb2312");
            process.waitFor();
            process.destroy();
            builder.setLength(0);
        }

    }
}

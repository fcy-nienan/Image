package windwos;

import CommonUtil.IOUtil;

import java.io.*;

public class SetAllPath {

    public static void main (String args[]) throws IOException {
        String allBinPath="D:\\ScheduledTask\\AllBInPath";
        BufferedReader reader= IOUtil.getBufferedReader(allBinPath);
        StringBuilder builder=new StringBuilder();
        builder.append("set PATH=%PATH%;");
        String msg=null;
        while ((msg=reader.readLine())!=null){
            builder.append(msg);
            builder.append(File.pathSeparator);
        }
        System.out.println(builder.toString());
    }
}

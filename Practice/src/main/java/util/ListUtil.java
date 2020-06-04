package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class ListUtil {
    public static void main(String args[]) {
        Process process=null;
        try{
            process=Runtime.getRuntime().exec("cmd /c move t1 t2");
            BufferedReader reader=new BufferedReader(new InputStreamReader(process.getErrorStream(),"gbk"));
            String msg;
            while ((msg=reader.readLine())!=null){
                System.out.println(msg);
            }
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void disList(List list){
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
    public static void disListLen(List list,int len){
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
            if (i>=len){
                return;
            }
        }
    }

}

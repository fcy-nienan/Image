package com.fcy.Util.FileStateCheck;

public class GitTask {
    public static void main(String args[]) {
        while (true){
            gitTask();
            try {
                Thread.sleep(60*30*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void gitTask(){
        Process process=null;
        try{
            process=Runtime.getRuntime().exec("cmd /c D:\\ScheduledTask\\AllTask.bat");
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (process!=null){
                process.destroy();
            }
        }
    }
}

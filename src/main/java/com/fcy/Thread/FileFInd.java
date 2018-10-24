package com.fcy.Thread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
/*
* 利用ForkInJoin查找文件
* */

public class FileFInd {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        long end;

        List<File> allRoot=getAllRoot();
        String filename="index.html";
        File file=new File("G:\\");
        ForkJoinPool pool=new ForkJoinPool();
        CountTask countTask=new CountTask(file,filename);
        pool.invoke(countTask);
//        do {
//            System.out.println("Active:"+pool.getActiveThreadCount());
//            System.out.println("steal:"+pool.getStealCount());
//            System.out.println("Grammer:"+pool.getQueuedTaskCount());
//            System.out.println("Running:"+pool.getRunningThreadCount());
//        }while(!countTask.isDone());
        countTask.join();
        List<String> allFile=countTask.get();

        end=System.currentTimeMillis();
        allFile.forEach(n->{
            System.out.println(n);
        });
        System.out.println("总计"+allFile.size()+"个文件");
        System.out.println("花费时间:"+((float)(end-start))/1000+"秒");
    }
    public static List getAllRoot(){
        File[] files=File.listRoots();
        List<File> all=new ArrayList();
        for(File f:files){
            all.add(f.getAbsoluteFile());
        }
        return all;
    }
    static class CountTask extends RecursiveTask<List> {
        private File file;
        private String filename;
        public CountTask(File file,String filename) {
            this.file = file;
            this.filename=filename;
        }
        public List<String> compute(){
            List<String> result=new ArrayList<>();
            File[] files=file.listFiles();
            if(files!=null&&files.length!=0){
                for(File s:files){
                    if(s.isDirectory()){
//                    System.out.println("分割任务:"+s.getName());
                        CountTask countTask=new CountTask(s,filename);
//                        countTask.fork();
                        invokeAll(countTask);
                        List<String> temp=countTask.join();
                        result.addAll(temp);
                    }else{
//                    System.out.println("file:"+s.getName()+"    filename:"+filename);
//                    System.out.println(s.getName().equals(filename));
                        if(s.getName().equals(filename)) {
                            String rs = s.getAbsolutePath();
//                            System.out.println("filename-------------------------------------"+rs);
                            result.add(rs);
                        }
                    }
                }
            }
            return result;
        }
    }
}


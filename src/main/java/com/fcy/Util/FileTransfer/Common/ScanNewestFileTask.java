package com.fcy.Util.FileTransfer.Common;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class ScanNewestFileTask implements Runnable{
    private static Logger logger = Logger.getLogger(ScanNewestFileTask.class.getName());
    private String path;
    private long timeout;
    private BlockingQueue<File> container;
    private volatile boolean stop;
    private volatile boolean canScan;
    public ScanNewestFileTask(String path, long timeout, BlockingQueue container){
        this.path=path;
        this.timeout=timeout;
        this.container=container;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()&&!stop){
            if (canScan) {
                File file = new File(path);
                scan(file);
            }
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void scan(File path){
        if (path.isDirectory()){
            File[] files=path.listFiles();
            for(File file:files){
                scan(file);
            }
        }else{
            this.container.add(path);
        }
    }
    public void startScan(){
        this.canScan=true;
    }
    public void stopScan(){
        this.canScan=false;
    }
    public void stop(){
        this.stop=true;
    }
}

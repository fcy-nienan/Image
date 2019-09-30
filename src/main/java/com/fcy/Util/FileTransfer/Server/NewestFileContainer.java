package com.fcy.Util.FileTransfer.Server;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class NewestFileContainer {
    private static Logger logger = Logger.getLogger(NewestFileContainer.class.getName());
    private static BlockingQueue<File> queue=new LinkedBlockingQueue();
    private HashMap<String,Boolean> map=new HashMap();
    public File take() throws InterruptedException {
        return queue.take();
    }
    public void add(File file){
        queue.add(file);
    }
}

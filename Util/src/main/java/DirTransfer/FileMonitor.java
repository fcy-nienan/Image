package DirTransfer;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/*
 * Author:fcy
 * Date:2020/2/5 13:41
 */
@Slf4j
public class FileMonitor implements Runnable {
    private long scanTimeout;
    private String dir;
    private boolean recursive;
    private File file;
    private Map<String, Long> lastModifiedMap;
    private BlockingQueue<ModifiedBean> modifiedQueue;
    private Thread thread;

    public FileMonitor(BlockingQueue modifiedQueue, String dir, long scanTimeout, boolean recursive) {
        this.modifiedQueue = modifiedQueue;
        this.dir = dir;
        this.scanTimeout = scanTimeout;
        this.recursive = recursive;
        this.file = new File(dir);
        this.lastModifiedMap = new HashMap<>();
        this.thread = new Thread(this);
    }
    public void start(){
        this.thread.start();
    }

    @Data
    @ToString
    class ModifiedBean {
        private String path;
        private long preModified;
        private long nowModified;

        public ModifiedBean(String path, long preModified, long nowModified) {
            this.path = path;
            this.preModified = preModified;
            this.nowModified = nowModified;
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                scan(file);
            } catch (Exception e) {
                log.warn("Encounter a not expected exception when scanning !");
                e.printStackTrace();
            }
            try {
                Thread.sleep(scanTimeout);
            } catch (InterruptedException e) {
                log.warn("FileMonitor has been interrupted!");
                e.printStackTrace();
            }
        }
        log.warn("FileMonitor finished!");
    }

    private void scan(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory() && recursive) {
                scan(f);
            } else {
                String name = f.getAbsolutePath();
                Long nowLastModified = f.lastModified();
                Long preLastModified = lastModifiedMap.get(name);
                if (!lastModifiedMap.containsKey(name)) {
                    lastModifiedMap.put(name, nowLastModified);
                    log.info("find a new file :" + name);
                    modifiedQueue.add(new ModifiedBean(name, 0, nowLastModified));
                } else {
                    if (!preLastModified.equals(nowLastModified)) {
                        lastModifiedMap.put(name, nowLastModified);
                        log.info("find a modified file:" + name);
                        modifiedQueue.add(new ModifiedBean(name, preLastModified, nowLastModified));
                    }
                }
            }
        }
    }
}

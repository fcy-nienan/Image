package CommonUtil;

import File.FileUtil;
import ThreadUtil.TMS;
import ThreadUtil.TP;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
@Slf4j
public class FcyUniqueID {
    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println(26*26*10*10);
//        FcyUniqueID uniqueID=new FcyUniqueID();
//        for (int i=0;i<100000;i++){
////            TMS.sleep(new Random().nextInt(4)*1000);
//            UniqueID uniqueID1 = uniqueID.computeUUID("fcy", 8);
////            log.info("count {} id {}",i,uniqueID1.getId());
//        }
//        System.out.println(uniqueID.size());
    }
    private static final String split="-";
    private static Set<String> allocatedID=new HashSet();
    private static BlockingQueue<UniqueID> queue=new LinkedBlockingQueue<>();
    private static String outputPath="D:\\uniqueID";
    private static ThreadPoolExecutor executor= TP.getTPE();
    static {
        executor.submit(new AppendToFile(queue,outputPath));
    }
    public int size(){
        return allocatedID.size();
    }
    private static class AppendToFile implements Runnable{
        private long time=5000;
        private BlockingQueue<UniqueID> queue;
        private String outPath;
        public AppendToFile(BlockingQueue<UniqueID> queue,String outPath){
            this.outPath=outPath;
            this.queue=queue;
        }
        @Override
        public void run() {
            while (true){
                try {
                    UniqueID id=queue.poll(time, TimeUnit.SECONDS);
                    FileUtil.appendToFile(outPath,JsonUtil.objectToString(id)+"\r\n");
                    log.info("{} has write into file {}",id.getId(),outPath);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //32位    8-8-8-8    string-number-string-number
    public UniqueID computeUUID(String account,int len){
        String uuid=getOneID(len);
        UniqueID id=new UniqueID();
        if (!allocatedID.contains(uuid)){
            id.setId(uuid);
            id.setApplyDate(new Date());
            id.setApplyAccount(account);
            id.setApplyFlag(true);
            synchronized (this) {
                allocatedID.add(uuid);
            }
            try {
                queue.put(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            id.setApplyFlag(false);
        }

        return id;
    }
    private String getOneID(int len){
        StringBuilder builder=new StringBuilder();
        builder.append(StringUtil.getRandomString(len,false)).
                append(split).
                append(StringUtil.getRandomNumber(len));
//                append(split).
//                append(StringUtil.getRandomString(len,false)).
//                append(split).
//                append(StringUtil.getRandomNumber(len));
        return builder.toString();
    }
}

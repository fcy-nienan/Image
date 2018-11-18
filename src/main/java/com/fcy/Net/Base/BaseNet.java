package com.fcy.Net.Base;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.*;
/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-16  0:42
 */
public class BaseNet {
    private static ConcurrentHashMap<Long,ProcessNumber> hashMap=new ConcurrentHashMap<>();
    private static int cycleCount=8000;
    private static int threadCount=25;
    private static final int coreSize=threadCount+5;
    private static final int maxSize=coreSize+5;
    public static Long init(){
        return 0l;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(coreSize,maxSize,0,TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        CountDownLatch countDownLatch=new CountDownLatch(threadCount);
        List<Future> futureList=new ArrayList(threadCount);
        for(int i=0;i<threadCount;i++){
            Future future=executor.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    long si=wagnyi();
                    long id=Thread.currentThread().getId();
                    hashMap.put(id,new ProcessNumber(0,si*cycleCount));
                    countDownLatch.countDown();
                    Long sum=new Long(0);
                    for(int i=0;i<cycleCount;i++){
                        wagnyi();
                    }
                    return sum;
                }
            });
            futureList.add(future);
        }
        countDownLatch.await();
        executor.submit(getFutureResult(futureList));
    }
    public static long wagnyi() throws IOException {
        Random random=new Random();
        int ran=random.nextInt(threadCount);
        URL url=new URL("https://music.163.com/#/user/fans?id=97526496");
        URLConnection connection=url.openConnection();
        InputStream inputStream=connection.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        File file=new File("G:\\test"+ran);
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file)
        ));
        String msg;
        long id=Thread.currentThread().getId();
        ProcessNumber processNumber=hashMap.get(id);
        if (processNumber==null){
            processNumber=new ProcessNumber(0,0);
            hashMap.put(id,processNumber);
        }
        long sum=processNumber.getPart();
        long portion=0;
        while((msg=reader.readLine())!=null){
            writer.write(msg);
            sum+=msg.length();
            portion+=msg.length();
        }
        processNumber.setPart(sum);
        hashMap.put(id,processNumber);
        writer.close();
        reader.close();
        return portion;
    }
    public static Runnable getFutureResult(List<Future> futures){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("start collect result...");
                for(Future future:futures){
                    try{
                        while(true){
                            if (future.isDone()&&!future.isCancelled()){
                                break;
                            }else{
                                process();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
    }
    public static void process(){
        long totalPart=0;
        long totalSum=0;
        Set<Map.Entry<Long,ProcessNumber>> set=hashMap.entrySet();
        for(Map.Entry<Long,ProcessNumber> e:set){
            totalPart+=(e.getValue().getPart());
            totalSum+=(e.getValue().getSum());
        }
        if (totalPart==0||totalSum==0)
            return;
        NumberFormat numberFormat=NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(2);
        float percentage=(float)totalPart/totalSum;
        StringBuilder stringBuilder=new StringBuilder("\r");
        stringBuilder
//                .append(totalSum/1024).append("KB")
//                .append(totalSum/1024/1024).append("MB")
                .append(totalSum/1024/1024/1024).append("GB")
// .append("/")
// .append(totalSum).append("---")
                .append(numberFormat.format(percentage))
                .append(totalPart/1024/1024/1024).append("GB");
        System.out.print(stringBuilder);
    }
}

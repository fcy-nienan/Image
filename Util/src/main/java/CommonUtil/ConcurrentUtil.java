package CommonUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-18  20:44
 */
@Slf4j
public class ConcurrentUtil {
    private static final long FutureResultSleep=3000;
    public static void main(String[] args) {

        ThreadPoolExecutor executor=new ThreadPoolExecutor(20,20,0,TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        List<Future> futures=new ArrayList<>();
        for(int i=0;i<14;i++){
            Future future=executor.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    int sum=0;
                    while(true){
                        int x=new Random().nextInt(10);
                        sum+=x;
                        if (sum>100000){
                            break;
                        }
                    }
                    return sum;
                }
            });
            futures.add(future);
        }
        executor.submit(getThreadResult(futures));
    }
    public static Callable getThreadResult(List futureList){
        return getThreadResult(futureList,FutureResultSleep);
    }
    public static Callable getThreadResult(List<Future> futureList,long time){
        return new Callable() {
            @Override
            public Object call() {
                int size=0;
                int real=futureList.size();
                List<Object> result=new ArrayList<>();
                while(true){
                    try{
                        for(int i=0;i<futureList.size();i++){
                            Future future=futureList.get(i);
                            if (future.isCancelled()){
                                size++;
                                continue;
                            }
                            if (future.isDone()&&!future.isCancelled()){
                                Object tmpR=future.get();
                                log.info("finished and result is %d !",tmpR);
                                size++;
                                result.add(tmpR);
                                futureList.remove(future);
                            }else{
                                Thread.sleep(time);
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if (size>=real)
                        break;
                }
                log.info("All Thread is Finished!--ThreadCount: %d ",size);
                return result;
            }
        };
    }
}

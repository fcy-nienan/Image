package MiniComponent.BigFileReader;

import lombok.extern.slf4j.Slf4j;

import java.nio.MappedByteBuffer;
@Slf4j
public class FcySync implements Runnable{
    private static final long DEFAULT_TIMEOUT=10000;
    private long timeout;
    private MappedByteBuffer mappedByteBuffer;
    public FcySync(MappedByteBuffer byteBuffer,long timeout){
        this.mappedByteBuffer=byteBuffer;
        this.timeout=timeout;
    }
    public FcySync(MappedByteBuffer byteBuffer){
        this(byteBuffer,DEFAULT_TIMEOUT);
    }
    @Override
    public void run () {
        while (true){
            try {
                Thread.sleep(timeout);
                flush();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean flushManually(){
        return flush();
    }
    private boolean flush(){
        if (mappedByteBuffer.isLoaded()){
            mappedByteBuffer.force();
            log.info("flush operation finished!");
            return true;
        }else{
            log.info("flush not operate because the file is not mapped");
            return false;
        }
    }
}

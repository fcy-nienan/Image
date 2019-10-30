package DesignPattern.MultiThread.Reactor;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-27  23:59
 */
@Data
public class Acceptor implements Runnable {
    private int port;
    private Selector selector;
    private BlockingQueue<InputSource> sources=new LinkedBlockingQueue<>();
    public Acceptor(Selector selector,int port){
        this.port=port;
        this.selector=selector;
    }
    public void addNewConnection(InputSource inputSource){
        sources.offer(inputSource);
    }
    @Override
    public void run() {
        while (true){
            InputSource inputSource=null;
            try{
                inputSource=sources.take();
            }catch (InterruptedException e){}
            if (sources!=null){
                Event acceptEvent=new Event();
                acceptEvent.setInputSource(inputSource);
                acceptEvent.setType(EventType.ACCEPT);
                selector.addEvent(acceptEvent);
            }
        }
    }
}

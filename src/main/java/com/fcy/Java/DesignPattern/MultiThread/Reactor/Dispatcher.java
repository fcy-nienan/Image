package com.fcy.Java.DesignPattern.MultiThread.Reactor;

<<<<<<< HEAD
import com.fcy.DesignPattern.MultiThread.Reactor.AbstractEventHandler;
import com.fcy.DesignPattern.MultiThread.Reactor.Event;
import com.fcy.DesignPattern.MultiThread.Reactor.EventType;
import com.fcy.DesignPattern.MultiThread.Reactor.Selector;

=======
>>>>>>> 4bdc1c1e1debb71d9dff9d6ef70e2ecb0e3d059f
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-27  23:26
 */
public class Dispatcher {
    private ConcurrentHashMap<EventType, AbstractEventHandler> eventHandlerMap=new ConcurrentHashMap<>();
    private Selector selector;
    public Dispatcher(Selector selector){
        this.selector=selector;
    }
    public void registerHandler(EventType type,AbstractEventHandler handler){
        eventHandlerMap.put(type,handler);
    }
    public void removeHandler(EventType type){
        eventHandlerMap.remove(type);
    }
    public void handleEvent(){
        dispatcher();
    }
    public void dispatcher(){
        while(true){
            List<Event> eventList=selector.select();
            for (Event event : eventList) {
                AbstractEventHandler handler=eventHandlerMap.get(event.getType());
                handler.handle(event);
            }
        }
    }
}

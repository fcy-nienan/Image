package com.fcy.Java.DesignPattern.MultiThread.Reactor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-27  23:36
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class AcceptEventHandler extends AbstractEventHandler {
    private Selector selector;
    @Override
    public void handle(Event event) {
        switch (event.getType()){
            case WRITE:{
                System.out.println("deal accept event");
                Event readEvent=new Event();
                readEvent.setType(EventType.READ);
                readEvent.setInputSource(event.getInputSource());
                selector.select();
            }
        }
    }
}

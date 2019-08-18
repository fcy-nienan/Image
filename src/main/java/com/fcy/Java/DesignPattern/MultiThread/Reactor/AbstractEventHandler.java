package com.fcy.Java.DesignPattern.MultiThread.Reactor;

import lombok.Data;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-27  23:35
 */
@Data
public abstract class AbstractEventHandler {
    private InputSource inputSource;
    public abstract void handle(Event event);
}

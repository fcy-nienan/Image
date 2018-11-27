package com.fcy.DesignPattern.MultiThread.Reactor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-27  23:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    private InputSource inputSource;
    private EventType type;
}

package com.fcy.Java.DesignPattern.MultiThread.Reactor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-27  23:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InputSource {
    private int id;
    private Object data;

}

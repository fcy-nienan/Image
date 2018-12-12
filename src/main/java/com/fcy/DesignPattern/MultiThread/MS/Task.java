package com.fcy.DesignPattern.MultiThread.MS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-29  1:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task {
    private String name;
    private int result;
//    private String operator;
//    private Object data;
//    private int time;
}

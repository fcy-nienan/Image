package com.fcy.DesignPattern.Interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  16:40
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator=new Calculator("a+b-c");
        Map<String,Integer> map=new HashMap();
        map.put("a",2);
        map.put("b",5);
        map.put("c",1);
        System.out.println(calculator.getResult(map));
    }
}

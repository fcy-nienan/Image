package DesignPattern.Interpreter;

import java.util.Map;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  13:34
 */
public abstract class Expression {
    abstract int interpreter(Map<String,Integer> map);
}

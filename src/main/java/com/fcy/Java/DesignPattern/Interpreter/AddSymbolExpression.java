package com.fcy.Java.DesignPattern.Interpreter;

import java.util.Map;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  13:39
 */
public class AddSymbolExpression extends SymbolExpression {
    public AddSymbolExpression(Expression left,Expression right){
        super(left,right);
    }
    @Override
    int interpreter(Map<String, Integer> map) {
        return left.interpreter(map)+right.interpreter(map);
    }
}

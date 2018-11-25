package com.fcy.DesignPattern.Interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  14:02
 */
public class Calculator {
    private Expression expression;
    public Calculator(String expStr){
        Stack<Expression> stack=new Stack<Expression>();
        char[] chars=expStr.toCharArray();
        Expression left=null;
        Expression right=null;
        for(int i=0;i<chars.length;i++){
            String value=String.valueOf(chars[i]);
            switch (chars[i]){
                case '+':{
                    left=stack.pop();
                    right=new VarExpression(String.valueOf(chars[++i]));
                    stack.push(new AddSymbolExpression(left,right));
                    break;
                }
                case '-':{
                    left=stack.pop();
                    right=new VarExpression(String.valueOf(chars[++i]));
                    stack.push(new SubSymbolExpression(left,right));
                    break;
                }
                default:{
                    stack.push(new VarExpression(value));
                }
            }
        }
        this.expression=stack.pop();
    }
    public int getResult(Map<String,Integer> map){
        return this.expression.interpreter(map);
    }


}

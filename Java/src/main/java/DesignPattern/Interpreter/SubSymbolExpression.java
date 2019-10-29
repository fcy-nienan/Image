package DesignPattern.Interpreter;

import java.util.Map;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  13:41
 */
public class SubSymbolExpression extends SymbolExpression {
    public SubSymbolExpression(Expression left,Expression right){
        this.left=left;
        this.right=right;
    }
    @Override
    int interpreter(Map<String, Integer> map) {
        return left.interpreter(map)-right.interpreter(map);
    }
}

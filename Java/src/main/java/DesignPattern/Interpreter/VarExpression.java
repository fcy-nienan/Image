package DesignPattern.Interpreter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  13:36
 */
@NoArgsConstructor
@AllArgsConstructor
public class VarExpression extends Expression {
    private String key;
    @Override
    public int interpreter(Map<String, Integer> map) {
        return map.get(key);
    }
}

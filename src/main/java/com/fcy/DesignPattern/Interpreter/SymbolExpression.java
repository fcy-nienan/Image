package com.fcy.DesignPattern.Interpreter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  13:38
 */
@NoArgsConstructor
@AllArgsConstructor
public abstract class SymbolExpression extends Expression {
    protected Expression left;
    protected Expression right;
}

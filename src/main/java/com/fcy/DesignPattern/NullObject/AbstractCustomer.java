package com.fcy.DesignPattern.NullObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:22
 */
public abstract class AbstractCustomer {
    protected String name;
    public abstract String getName();
    public abstract boolean isNil();
}

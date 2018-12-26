package com.fcy.DataStruct.Tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-12-15  22:19
 */
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Tree {
    public Tree left;
    public Tree right;
    private Object value;
    public Tree(Object object){
        this.value=object;
    }
}

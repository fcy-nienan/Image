package com.fcy.Algorithm.BaseStruct.Tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-02-14  0:51
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Node {
    private Object data;
    private Node left;
    private Node right;
}

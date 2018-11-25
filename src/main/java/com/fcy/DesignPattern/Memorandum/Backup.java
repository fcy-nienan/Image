package com.fcy.DesignPattern.Memorandum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Backup {
    private String name;
    private int age;
}

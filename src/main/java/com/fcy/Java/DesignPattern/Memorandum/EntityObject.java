package com.fcy.Java.DesignPattern.Memorandum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  21:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntityObject {
    private String name;
    private int age;
    public void record(Backup backup){
        backup.setName(name);
        backup.setAge(age);
    }
    public void backup(Backup backup){
        this.name=backup.getName();
        this.age=backup.getAge();
    }
}

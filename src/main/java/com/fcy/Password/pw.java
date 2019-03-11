package com.fcy.Password;

import com.fcy.Java.JDBC.GenericDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-03-09  17:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class pw {
    private long id;
    private String name;
    private String password;
    private String first_insert;
}

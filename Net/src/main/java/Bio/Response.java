package com.fcy.Net.Bio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.OutputStream;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-11  0:02
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private OutputStream outputStream;

}

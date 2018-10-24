package com.fcy.Net.Bio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-11  0:08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RequestWrapper {
    private Request request;
    private Response response;
    protected void service(Request request,Response response){

    }
}

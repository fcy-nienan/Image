package com.fcy.Net.Bio;

import java.util.HashMap;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-14  22:36
 */
public class HttpHeader {
    private HashMap<String,String> map;
    private List<String> headers;
    public HttpHeader(List<String> list){
        this.headers=list;
    }
}

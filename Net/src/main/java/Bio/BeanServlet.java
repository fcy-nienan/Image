package com.fcy.Net.Bio;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-11  0:00
 */
public interface BeanServlet {
    void init();
    void service(Request request,Response response);
    void destory();
}

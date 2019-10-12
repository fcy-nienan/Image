package com.fcy.Notes;

import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-17  20:53
 */
public class ThreadAssembly {
    public static void main(String[] args) throws InterruptedException {
        fcy();
    }
    public static void fcy() throws InterruptedException {
        List<Object> objects=getTest();
        List<test> tests=(List)(objects);
    }
    public static List<Object> getTest(){
        List<Object> objects=new ArrayList<>();
        objects.add(new test());
        return objects;
    }
    static class test{

    }

}

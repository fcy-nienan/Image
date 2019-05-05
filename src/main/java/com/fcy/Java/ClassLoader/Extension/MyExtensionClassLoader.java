package com.fcy.Java.ClassLoader.Extension;

import java.net.URL;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-06  0:20
 */
public class MyExtensionClassLoader extends ClassLoader {

    @Override
    protected URL findResource(String name) {
        return super.findResource(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}

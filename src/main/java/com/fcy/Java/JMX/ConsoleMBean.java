package com.fcy.Java.JMX;

public interface ConsoleMBean {
    void setState(String state);
    String getState();
    void start();
    void stop();
    void dis(String i);
    default void test(){

    }
}

package com.fcy.JMX;

public interface ConsoleMBean {
    public void setState(String state);
    public String getState();
    public void start();
    public void stop();
    public void dis(String i);
    public void test();
}

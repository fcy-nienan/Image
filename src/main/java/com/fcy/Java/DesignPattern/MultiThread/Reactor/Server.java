package com.fcy.Java.DesignPattern.MultiThread.Reactor;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-28  0:02
 */
public class Server {
    private Selector selector=new Selector();
    private Dispatcher dispatcher=new Dispatcher(selector);
    private Acceptor acceptor;
    public Server(int port){
        acceptor=new Acceptor(selector,port);
    }
    public void start(){
        dispatcher.registerHandler(EventType.ACCEPT,new AcceptEventHandler(selector));
        new Thread(acceptor).start();
        dispatcher.handleEvent();
    }
}

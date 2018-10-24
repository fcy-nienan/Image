package com.fcy.Net.Bio;

import com.sun.corba.se.impl.orbutil.concurrent.ReentrantMutex;

import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-08-27  14:06
 */
public class BioSocketParam {
    public static void main(String args[]) throws SocketException {
        testSocketParam();
    }
    public static void testSocketParam() throws SocketException {
        Socket socket=new Socket();
        //是否立即发送数据
        System.out.println("tcpNoDelay:"+socket.getTcpNoDelay());
//        是否重用地址
        System.out.println("ReuseAddress:"+socket.getReuseAddress());
//        接受数据的等待超时时间
        System.out.println("TimeOut:"+socket.getSoTimeout());
//        close是否立即关闭底层socket
        System.out.println("Linger:"+socket.getSoLinger());
//        接受缓冲区大小
        System.out.println("ReceiveBufferSize:"+socket.getReceiveBufferSize());
//        发送缓冲区大小
        System.out.println("SendBufferSize:"+socket.getSendBufferSize());
//        是否保持连接 长时间处于空闲状态的socket是否需要自动关闭
        System.out.println("KeepAlive:"+socket.getKeepAlive());
//        是否发送紧急数据
        System.out.println("OOBInLine:"+socket.getOOBInline());
    }
}

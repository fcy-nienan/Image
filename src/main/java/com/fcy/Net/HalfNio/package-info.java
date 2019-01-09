package com.fcy.Net.HalfNio;
/**
*
 * 同步阻塞 调用io,当前线程阻塞
 * 同步非阻塞 调用io,返回trueorfalse
 * 异步阻塞 调用io，阻塞在selector调用上，io来临时通知当前线程
 * 异步非阻塞
 *
 *
 * 传统的BIO是一点有客户端连接来临,如果要读取数据,如果此时客户端没有数据就必须阻塞
 * 而NIO是用一个线程管理所有的客户端连接，当有数据来临的时候会通知selector，然后唤醒
* */
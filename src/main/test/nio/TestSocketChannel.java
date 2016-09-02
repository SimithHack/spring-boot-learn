package com.xie.learn.java.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by xfq on 16/8/16.
 */
public class TestSocketChannel {
    //测试blockingLock，锁修改blocking mode行为
    @Test
    public void testBlockingLock() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost",1234));
        SocketChannel socket = null;
        Object blockLock = serverChannel.blockingLock();
        //自己同步此对象
        synchronized (blockLock){
            //此线程拥有blockLock锁，mode不能修改
            boolean preState = serverChannel.isBlocking();
            System.out.println("before");
            serverChannel.configureBlocking(true);
            System.out.println("end");
            socket = serverChannel.accept();
            serverChannel.configureBlocking(preState);
        }
        //锁被释放，模式允许修改
        //do something
        if(socket!=null){
            System.out.println("socket is not null");
        }
    }
    @Test
    public void noBlockingMode() throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 1234));
        serverSocketChannel.configureBlocking(false);
        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(null==socketChannel){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("connected");
            }
        }
    }
    @Test
    public void connectComplete() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 1234));
        while(!socketChannel.finishConnect()){
            System.out.println("connection is not finished");
        }
        System.out.println("now we can use channel to transfer data");
        socketChannel.close();
    }
}

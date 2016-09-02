package com.xie.learn.java.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;

/**
 * Created by xfq on 16/8/20.
 */
public class TestSelector {
    @Test
    public void testCheckOps() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 1234));
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_READ);
        if((key.readyOps()& SelectionKey.OP_READ)!=0){
            //表明现在可以读取了
        }
    }

}

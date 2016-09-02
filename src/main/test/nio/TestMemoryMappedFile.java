package com.xie.learn.java.nio;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import java.nio.channels.FileChannel.MapMode;

/**
 * Created by xfq on 16/8/6.
 */
public class TestMemoryMappedFile {
    private String msg = "this is original infomation .";
    @Test
    public void testMemoryMappedFile() throws IOException {
        File temp = File.createTempFile("wocao",null);
        RandomAccessFile file = new RandomAccessFile(temp,"rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put(msg.getBytes());
        buffer.flip();
        channel.write(buffer, 0);
        buffer.clear();
        buffer.put(msg.getBytes());
        buffer.flip();
        channel.write(buffer,163840);
        //channel.force(false);
        //测试三种MapMode
        MappedByteBuffer ro = channel.map(MapMode.READ_ONLY, 0, channel.size());
        MappedByteBuffer rw = channel.map(MapMode.READ_WRITE,0,channel.size());
        MappedByteBuffer rp = channel.map(MapMode.PRIVATE,0,channel.size());
        System.out.println("init");
        print(ro, rw, rp);
        System.out.println("modify rw");
        rw.position(10);
        rw.put(" R/W".getBytes());
        rw.position(163850);
        rw.put(" R/W".getBytes());
        rw.force();
        print(ro, rw, rp);

        System.out.println("modify rp");
        rp.position(10);
        rp.put(" RP".getBytes());
        print(ro, rw, rp);

        System.out.println("modify channel");
        buffer.clear();
        buffer.put(" Channel ".getBytes());
        buffer.flip();
        channel.write(buffer, 10);
        print(ro, rw, rp);

        System.out.println("modify channel(2)");
        buffer.clear();
        buffer.put(" Channel(2) ".getBytes());
        buffer.flip();
        channel.write(buffer, 163850);
        print(ro, rw, rp);

    }
    public void print(ByteBuffer ro,ByteBuffer rw,ByteBuffer rp){
        printBuffer(ro,"ro=");
        printBuffer(rw,"rw=");
        printBuffer(rp,"rp=");
    }
    public void printBuffer(ByteBuffer bf,String prefix){
        System.out.print(prefix);
        int nulls=0;
        for(int i=0;i<bf.limit();i++){
            char c = (char)bf.get(i);
            if(c=='\u0000'){
                nulls++;
                continue;
            }
            if(nulls!=0){
                System.out.print("|["+nulls+" nulls]|");
                nulls=0;
            }
            System.out.print(c);
        }
        System.out.println();
    }
}

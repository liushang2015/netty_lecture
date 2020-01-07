package com.liushang.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        socketChannel.configureBlocking(true);

        String fileName = "/opt/application/netty/new_client/55_.mp4";

        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileChannel fileChannel  = fileInputStream.getChannel();

        long startTime = System.currentTimeMillis();

        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送字节数：" + transferCount + ",耗时：" + (System.currentTimeMillis() - startTime));

        fileChannel.close();
    }
}

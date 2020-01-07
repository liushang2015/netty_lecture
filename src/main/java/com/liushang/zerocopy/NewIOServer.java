package com.liushang.zerocopy;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * linux 系统才能使用
 */
public class NewIOServer {
    public static void main(String[] args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(8899);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(address);


        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);


        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true); // 这里设置为阻塞模式
            int readCount = 0;


            while (-1 != readCount) {
                readCount = socketChannel.read(byteBuffer);


                // 这里一定要调用下rewind方法，将position重置为0开始位置
                byteBuffer.rewind();
            }
        }
    }
}

package com.liushang.zerocopy;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * linux ϵͳ����ʹ��
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
            socketChannel.configureBlocking(true); // ��������Ϊ����ģʽ
            int readCount = 0;


            while (-1 != readCount) {
                readCount = socketChannel.read(byteBuffer);


                // ����һ��Ҫ������rewind��������position����Ϊ0��ʼλ��
                byteBuffer.rewind();
            }
        }
    }
}

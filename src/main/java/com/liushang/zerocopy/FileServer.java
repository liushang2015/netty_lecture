package com.liushang.zerocopy;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
public class FileServer {
    public static void main(String[] args) throws IOException {


        startServer();
    }

    public static void startServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8899));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0)
        {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext())
            {
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable())
                {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    try (FileInputStream in = new FileInputStream("F:\\学习资料\\netty高并发-张龙\\ZOL手机数据(1).rar")){

                        long size = in.available();
                        long num = 0;
                        long begin = 0;
                        while ( (num = in.getChannel().transferTo(begin,size,socketChannel))!=0)
                        {
                            size-=num;
                            begin += num;
                        }
                        socketChannel.close();

                    }
                    catch (IOException e){e.printStackTrace();}

                }
            }
        }


    }
}

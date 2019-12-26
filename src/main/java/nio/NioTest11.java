package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest11 {
    public static void main(String[] args) throws IOException {
        int [] prots = new int[5];
        prots[0] = 5000;
        prots[1] = 5001;
        prots[2] = 5002;
        prots[3] = 5003;
        prots[4] = 5004;
        Selector selector = Selector.open();

        for (int i = 0; i < prots.length ; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(prots[i]);
            serverSocket.bind(address);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口："+prots[i]);
        }
        while (true){
            int numbers = selector.select();
            System.out.println("numbers: "+numbers);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys: "+selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                /*
                如果此密钥的通道不支持套接字接受操作，则此方法始终返回<tt>false</tt>
                 */
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector,SelectionKey.OP_READ);

                    iterator.remove();

                    System.out.println("获得客户端连接："+socketChannel);
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                    int byteRead =0;
                    while(true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int read= socketChannel.read(byteBuffer);
                        if(read<=0){
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        byteRead+=read;
                    }
                    System.out.println("读取："+byteRead+",来自于："+socketChannel);
                    /**
                     * 表示用完当前事件之后，已经消费掉了，一定要删除掉
                     */
                    iterator.remove();
                }

            }
        }
    }
}

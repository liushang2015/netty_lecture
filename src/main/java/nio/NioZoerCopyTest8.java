package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioZoerCopyTest8 {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("F:\\学习资料\\netty高并发-张龙\\02_Netty宏观理解.mp4");
            FileOutputStream fileOutputStream = new FileOutputStream("F:\\学习资料\\netty高并发-张龙\\02_Netty宏11.mp4");

            FileChannel inputChannel = fileInputStream.getChannel();
            FileChannel outputChannel = fileOutputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

            while(true){
                buffer.clear();
                int read = inputChannel.read(buffer);
                System.out.println(read+" , "+buffer.position()+" , "+buffer.limit()+" , "+buffer.capacity());
                if(-1 == read){
                    break;
                }

                buffer.flip();

                outputChannel.write(buffer);
            }
            outputChannel.close();
            inputChannel.close();
            fileOutputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

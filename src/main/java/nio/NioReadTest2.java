package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioReadTest2 {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("Niotest2.txt");

            FileChannel fileChannel =fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            fileChannel.read(byteBuffer);

            byteBuffer.flip();

            while(byteBuffer.remaining()>0){
                byte b = byteBuffer.get();
                System.out.println((char) b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

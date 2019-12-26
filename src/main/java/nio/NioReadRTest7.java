package nio;

import java.nio.ByteBuffer;

/**
 * 只读buff,我们随时将一个普通Buffer调用asReadOnlyBuffer方法返回一个只读Buffer
 * 但不能将一个只读Buffer转换为读写Buffer
 */
public class NioReadRTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.getClass());

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readBuffer = buffer.asReadOnlyBuffer();

        System.out.println(readBuffer.getClass());

        readBuffer.position(0);

    }
}

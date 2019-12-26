package nio;

import java.nio.ByteBuffer;

/**
 * ֻ��buff,������ʱ��һ����ͨBuffer����asReadOnlyBuffer��������һ��ֻ��Buffer
 * �����ܽ�һ��ֻ��Bufferת��Ϊ��дBuffer
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

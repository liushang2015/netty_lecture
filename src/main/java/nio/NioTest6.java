package nio;

import java.nio.ByteBuffer;

/**
 * Slice Buff与原有buff共享相同的底层数组
 */
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);

        ByteBuffer sliceBuffer = buffer.slice();

        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get();
            b *=2;
            sliceBuffer.put(i,b);
        }
        buffer.position(0);
        buffer.limit(buffer.capacity());
        System.out.println("=====================");

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}

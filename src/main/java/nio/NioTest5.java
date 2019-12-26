package nio;

import java.nio.ByteBuffer;

public class NioTest5 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(16);
        buffer.putDouble(23.412);
        buffer.putChar('Œ“');
        buffer.putLong(123333L);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getLong());

    }
}

package nio;

import com.google.common.primitives.Chars;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class NioCopyMapTest13 {
    public static void main(String[] args) throws Exception {
        String inputFile = "Niotest13_in.txt";
        String outputFile = "Niotest13_out.txt";
        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");

        long inputLength = new File(inputFile).length();

        FileChannel inpitFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        MappedByteBuffer inputData = inpitFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);
//
        System.out.println("======================");
        Charset.availableCharsets().forEach((k,v)->{
            System.out.println(k +" , "+v);
        });
        System.out.println("======================");

        Charset charset = Charset.forName("utf-8");
        //½âÂë
        CharsetDecoder decoder = charset.newDecoder();
        //±àÂë
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(inputData);
        ByteBuffer outputData = encoder.encode(charBuffer);

        outputFileChannel.write(outputData);
        outputRandomAccessFile.close();
        inputRandomAccessFile.close();

    }

}

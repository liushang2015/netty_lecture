package nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class NioCopyMapTest13 {
    public static void main(String[] args) throws Exception

    {
        String inputFile = "Niotest13_in.txt";
        String outputFile = "Niotest13_out.txt";
        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");

        long inputLength = new File(inputFile).length();

        FileChannel inpitFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        MappedByteBuffer inputData = inpitFileChannel.map(FileChannel.MapMode.READ_ONLY,0,inputLength);

        Charset charset = Charset.forName("utf-8");
    }

}

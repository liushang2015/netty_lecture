package com.liushang.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class OldClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8899);
            String fileName = "/opt/application/netty/new_client/55_.mp4";
            InputStream inputStream = new FileInputStream(fileName);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            byte [] buffer = new byte[4096];
            long readCount ;
            long total =0;

            long startTime = System.currentTimeMillis();

            while((readCount = inputStream.read(buffer))>= 0){
                total += readCount;
                dataOutputStream.write(buffer);
            }
            System.out.println("发送字节数："+total+",耗时："+(System.currentTimeMillis()-startTime));
            dataOutputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

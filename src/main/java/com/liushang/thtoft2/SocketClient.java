package com.liushang.thtoft2;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String args[]) throws Exception {
// 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 8899;
        String message = "Hello1 Python SocketServer ";
        String socketServerMsg = getSocketServerMsg(host, port, message);
        System.out.println("接收的服务端信息：" + socketServerMsg);
    }

    public static String getSocketServerMsg(String host, int port, String message) throws Exception {
// 与服务端建立连接
        Socket socket = new Socket(host, port);
// 获得输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pWriter = new PrintWriter(outputStream);
        pWriter.write(message);
        pWriter.flush();
//通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
        socket.shutdownOutput();
// 获得输入流
        InputStream inputStream = socket.getInputStream();

        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
//注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        inputStream.close();
        outputStream.close();
        socket.close();

        return sb.toString();
    }

}

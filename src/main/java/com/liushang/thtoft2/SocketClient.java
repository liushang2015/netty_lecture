package com.liushang.thtoft2;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String args[]) throws Exception {
// Ҫ���ӵķ����IP��ַ�Ͷ˿�
        String host = "127.0.0.1";
        int port = 8899;
        String message = "Hello1 Python SocketServer ";
        String socketServerMsg = getSocketServerMsg(host, port, message);
        System.out.println("���յķ������Ϣ��" + socketServerMsg);
    }

    public static String getSocketServerMsg(String host, int port, String message) throws Exception {
// �����˽�������
        Socket socket = new Socket(host, port);
// ��������
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pWriter = new PrintWriter(outputStream);
        pWriter.write(message);
        pWriter.flush();
//ͨ��shutdownOutput���ٷ������Ѿ����������ݣ�����ֻ�ܽ�������
        socket.shutdownOutput();
// ���������
        InputStream inputStream = socket.getInputStream();

        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
//ע��ָ�������ʽ�����ͷ��ͽ��շ�һ��Ҫͳһ������ʹ��UTF-8
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        inputStream.close();
        outputStream.close();
        socket.close();

        return sb.toString();
    }

}

package com.liushang.thtoft2;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.omg.CORBA.TIMEOUT;
import thrift.generated.PersonService;
import thrift.generated.service.demo.Hello;

public class HelloClient {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT =8899;
    public static final int TIMEOUT =30000;

    public void startClient(String word) {
//        TTransport transport = new TFramedTransport(new TSocket("localhost",8899),600);
//        TProtocol protocol = new TCompactProtocol(transport);
//        PersonService.Client client = new PersonService.Client(protocol);
//
        TTransport transport = null;
        try {

            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
// Э��Ҫ�ͷ����һ��

            TProtocol protocol = new TBinaryProtocol(transport);
// TProtocol protocol = new TCompactProtocol(transport);
// TProtocol protocol = new TJSONProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);
            transport.open();
            String result = client.helloString(word);
            System.out.println("Thrify client result =: " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    public static void main(String[] args) {
        HelloClient client = new HelloClient();
        client.startClient("Michael");

    }

}

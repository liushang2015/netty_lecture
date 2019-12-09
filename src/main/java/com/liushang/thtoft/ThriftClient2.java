package com.liushang.thtoft;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import thrift.generated.PersonService;

public class ThriftClient2 {
    public void startClient() {
        TTransport transport;
        try {
            System.out.println("thrift client connext server at 8989 port ");
            transport = new TSocket("127.0.0.1", 8899);
            TProtocol protocol = new TBinaryProtocol(transport);
            PersonService.Client client = new PersonService.Client(protocol);
            transport.open();
            System.out.println(client.getPersonByUsername(" Thrift"));
            transport.close();
            System.out.println("thrift client close connextion");
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("thrift client init ");
        ThriftClient2 client = new ThriftClient2();
        client.startClient();
        System.out.println("thrift client start ");

        System.out.println("thrift client end ");
    }
}

package com.liushang.thtoft;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.generated.PersonService;

public class ThtiftServer {
    public static void main(String[] args) throws TTransportException {
        //��������ServerSocket
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        //�߿��õ�Server  arg������������Ϣ
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        //������ �����Ƿ�����ʵ�ֵ�����
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
        //Э�鹤�� ѹ���Ĺ���
        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        //�������processor��Ϊ����
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        System.out.println("Thrift Server Started!");
        //����һ����ѭ��
        server.serve();
    }
}

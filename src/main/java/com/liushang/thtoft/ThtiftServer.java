package com.liushang.thtoft;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.generated.PersonService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThtiftServer {
    public static void main(String[] args) throws TTransportException {
        //��������ServerSocket
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        //�߿��õ�Server  arg������������Ϣ
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        //������ �����Ƿ�����ʵ�ֵ�����
        PersonService.Processor<PersonServiceImpl> processor =
                new PersonService.Processor<>(new PersonServiceImpl());
        //TCompactProtocolЭ��㹤����������ѹ��Э�飬
        // ��������ɵ��ֽ����������Ŭ��ѹ����������С���ֽ���ѹ��Э����ɴ�һ�˷�����һ��
        arg.protocolFactory(new TCompactProtocol.Factory());
        //TFramedTransport�����  ��FrameΪ��λ���д��䣬������ʽ������ʹ�á�
        arg.transportFactory(new TFramedTransport.Factory());
        //�������processor��Ϊ����
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        System.out.println("Thrift Server Started!");
        //����һ����ѭ��
        server.serve();

    }


}

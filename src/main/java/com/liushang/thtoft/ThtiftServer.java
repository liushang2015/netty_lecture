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
        //非阻塞的ServerSocket
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        //高可用的Server  arg用来构建好信息
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        //处理器 泛型是服务器实现的类型
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
        //协议工厂 压缩的工厂
        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        //将上面的processor作为参数
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        System.out.println("Thrift Server Started!");
        //开启一个死循环
        server.serve();
    }
}

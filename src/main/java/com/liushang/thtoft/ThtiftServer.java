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
        //非阻塞的ServerSocket
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        //高可用的Server  arg用来构建好信息
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        //处理器 泛型是服务器实现的类型
        PersonService.Processor<PersonServiceImpl> processor =
                new PersonService.Processor<>(new PersonServiceImpl());
        //TCompactProtocol协议层工厂，二进制压缩协议，
        // 它会把生成的字节码最近最大的努力压缩成容量最小的字节码压缩协议组成从一端发往另一端
        arg.protocolFactory(new TCompactProtocol.Factory());
        //TFramedTransport传输层  以Frame为单位进行传输，非阻塞式服务中使用。
        arg.transportFactory(new TFramedTransport.Factory());
        //将上面的processor作为参数
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        System.out.println("Thrift Server Started!");
        //开启一个死循环
        server.serve();

    }


}

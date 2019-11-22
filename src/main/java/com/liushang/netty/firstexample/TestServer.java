package com.liushang.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty推荐2个线程组完成
 */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 2个事件循环组
         * 这个线程组是不断的从客户端接收连接，它不对连接做任何处理，接收连接后转给workerGroup
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        /**
         * 由这个线程组完成后续的处理，如获取连接的参数,实际的业务处理,把结果返回给客户端
         */
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }
}

package com.liushang.netty.sixthexample;

import com.liushang.netty.thirdexample.MyChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new TestClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8899).sync();
            channelFuture.channel().closeFuture().sync();
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//            for (; ; ) {
//                channel.writeAndFlush(br.readLine() + " \r\n");
//            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}

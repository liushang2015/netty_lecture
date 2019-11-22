package com.liushang.netty.fourthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /**
         * 空闲状态监测处理器
         * 读取程序空闲时间 读空闲5秒
         * 写入程序空闲时间 写空闲7秒
         * 所有空闲时间 读写空闲10秒
         * 表示一秒的时间单位
         */
        pipeline.addLast(new IdleStateHandler(5,7,3, TimeUnit.SECONDS));
        pipeline.addLast(new MyServerHandler() );
    }
}

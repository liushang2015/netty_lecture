package com.liushang.netty.thirdexample;

import com.liushang.netty.secondexample.MyServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyChatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //���� ���ݷָ������н���
        pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        //����
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        //����
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        //�Լ��Ĵ�����
        pipeline.addLast(new MyChatServerHandler());
    }
}

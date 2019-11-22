package com.liushang.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import jdk.nashorn.internal.runtime.GlobalConstants;

import java.util.UUID;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    //��������һ��һ��Channel�����
    private  static  ChannelGroup channelGroup =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if(channel!=ch){
                ch.writeAndFlush(channel.remoteAddress()+" ���͵���Ϣ\n"+msg+"\n");
            }else{
                ch.writeAndFlush("���Լ��� "+msg+"\n");
            }
        });
    }

    //�ͻ��˷���һ�����ӵ�ʱ��
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("[������] - "+channel.remoteAddress()+" ����\n");
        channelGroup.add(channel);
    }

    //���Ӷ��˽��д���
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("[������] - "+channel.remoteAddress()+" �뿪\n");
//        channelGroup.remove(channel);���Զ�ִ��
        System.out.println(channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" ����");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" ����");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //����������쳣�����������ӹرյ�
        cause.printStackTrace();
        ctx.close();
    }
}

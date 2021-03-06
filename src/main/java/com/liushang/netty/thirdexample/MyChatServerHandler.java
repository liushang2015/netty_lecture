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
    //用来保存一个一个Channel对象的
    private  static  ChannelGroup channelGroup =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if(channel!=ch){
                ch.writeAndFlush(channel.remoteAddress()+" 发送的消息\n"+msg+"\n");
            }else{
                ch.writeAndFlush("【自己】 "+msg+"\n");
            }
        });
    }

    //客户端发起一个连接的时候
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("[服务器] - "+channel.remoteAddress()+" 加入\n");
        channelGroup.add(channel);
    }

    //连接断了进行处理
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("[服务器] - "+channel.remoteAddress()+" 离开\n");
//        channelGroup.remove(channel);会自动执行
        System.out.println(channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //如果出现了异常，会吧这个连接关闭掉
        cause.printStackTrace();
        ctx.close();
    }
}

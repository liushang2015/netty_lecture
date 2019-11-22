package com.liushang.netty.fourthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 检测空闲状态事件
     * 触发某一个事件后得到调用
     * @param ctx 上下文对象
     * @param evt 事件对象
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event=(IdleStateEvent)evt;
            String eventType = null;

            /**
             * READER_IDLE 暂时没有收到数据
             * WRITER_IDLE 有段时间没有发送数据。
             * ALL_IDLE 有段时间没有接收或发送数据。
             */
            switch (event.state()){
                case READER_IDLE:
                    eventType="读空闲";
                    break;
                case WRITER_IDLE:
                    eventType="写空闲";
                    break;
                case  ALL_IDLE:
                    eventType="读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress()+"超时事件： "+eventType);
            ctx.channel().close();
        }
    }
}

package com.liushang.netty.fourthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * ������״̬�¼�
     * ����ĳһ���¼���õ�����
     * @param ctx �����Ķ���
     * @param evt �¼�����
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event=(IdleStateEvent)evt;
            String eventType = null;

            /**
             * READER_IDLE ��ʱû���յ�����
             * WRITER_IDLE �ж�ʱ��û�з������ݡ�
             * ALL_IDLE �ж�ʱ��û�н��ջ������ݡ�
             */
            switch (event.state()){
                case READER_IDLE:
                    eventType="������";
                    break;
                case WRITER_IDLE:
                    eventType="д����";
                    break;
                case  ALL_IDLE:
                    eventType="��д����";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress()+"��ʱ�¼��� "+eventType);
            ctx.channel().close();
        }
    }
}

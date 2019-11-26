package com.liushang.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int randdomInt = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        switch (randdomInt){
            case 0:
                 myMessage =MyDataInfo.MyMessage.newBuilder()
                        .setDataType(MyDataInfo.MyMessage.DateType.PersonType)
                        .setPerson(MyDataInfo.Person.newBuilder()
                                .setName("张三")
                                .setAge(20)
                                .setAddress("北京")
                                .build())
                        .build();
                 break;
            case 1:
                myMessage =MyDataInfo.MyMessage.newBuilder()
                        .setDataType(MyDataInfo.MyMessage.DateType.DogType)
                        .setDog(MyDataInfo.Dog.newBuilder()
                        .setName("狗狗")
                        .setAge(3)
                        .build())
                        .build();
                break;
             default:
                 myMessage =MyDataInfo.MyMessage.newBuilder()
                         .setDataType(MyDataInfo.MyMessage.DateType.CatType)
                         .setCat(MyDataInfo.Cat.newBuilder()
                                 .setName("猫猫")
                         .setCity("武汉"))
                         .build();


        }

//        son.newBuilder().setName("张三").setAge(20).setAddress("北京").build();
        ctx.channel().writeAndFlush(myMessage);
    }
}

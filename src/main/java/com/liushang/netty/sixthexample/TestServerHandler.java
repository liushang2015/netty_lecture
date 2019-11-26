package com.liushang.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DateType dateType = msg.getDataType();

        switch (dateType) {
            case PersonType:
                MyDataInfo.Person person = msg.getPerson();
                System.out.println(person.getName());
                System.out.println(person.getAge());
                System.out.println(person.getAddress());
                break;

            case DogType:
                MyDataInfo.Dog dog = msg.getDog();
                System.out.println(dog.getName());
                System.out.println(dog.getAge());
                break;
            default:
                MyDataInfo.Cat cat = msg.getCat();
                System.out.println(cat.getName());
                System.out.println(cat.getCity());

        }
//        System.out.println(msg.getName());
//        System.out.println(msg.getAge());
//        System.out.println(msg.getAddress());
    }
}

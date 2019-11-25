package com.liushang.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBuffTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        //生成构建起对象 返回Builder对象，通过Builder对象不断地进行set,set,set完成属性的赋值
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三").setAge(20).setAddress("北京").build();

        //转换成字节数组  这些字节数组可以在网络上传输 比如说可以从服务器端传输到客户端，客户端传输到服务器端   
        byte[] student2ByteArray = student.toByteArray();
        //从字节数组进行反序列化
        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}

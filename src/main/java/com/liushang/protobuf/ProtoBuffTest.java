package com.liushang.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBuffTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        //���ɹ�������� ����Builder����ͨ��Builder���󲻶ϵؽ���set,set,set������Եĸ�ֵ
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("����").setAge(20).setAddress("����").build();

        //ת�����ֽ�����  ��Щ�ֽ���������������ϴ��� ����˵���Դӷ������˴��䵽�ͻ��ˣ��ͻ��˴��䵽��������   
        byte[] student2ByteArray = student.toByteArray();
        //���ֽ�������з����л�
        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}

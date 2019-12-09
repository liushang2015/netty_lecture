package com.liushang.grpc;

import com.liushang.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;

public class GrpcClient {

    public static void main(String[] args) {
        //���������ӵ������
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext(true).build();
        //�ͻ��˷��ʷ������˵�stub
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        //

        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);

//        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
//        //��ʾ����������Ӧ�Ľ��
//
//
//        System.out.println(myResponse.getRealname());
//        System.out.println("=======================");
//
//        Iterator<StudentResponse> iterator=blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(90).build());
//
//
//        while(iterator.hasNext()){
//            StudentResponse studentResponse = iterator.next();
//            System.out.println(studentResponse.getName()+", "+studentResponse.getAge()+", "+studentResponse.getCity());
//        }


//        StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
//            @Override
//            public void onNext(StudentResponseList value) {
//                value.getStudentResponseList().forEach(studentResponse -> {
//                    System.out.println(studentResponse.getName());
//                    System.out.println(studentResponse.getAge());
//                    System.out.println(studentResponse.getCity());
//                    System.out.println("****�������ͻ���������ս��*******");
//                });
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.out.println(t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("onCompleted!");
//            }
//        };
//
//        StreamObserver<StudentRequest> studentRequestStreamObserver = stub.getStudentsWrpperByages(studentResponseListStreamObserver);
//
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(21).build());
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(22).build());
//        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(23).build());
//        studentRequestStreamObserver.onCompleted();


        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        });


        for (int i = 0; i < 10; i++) {
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

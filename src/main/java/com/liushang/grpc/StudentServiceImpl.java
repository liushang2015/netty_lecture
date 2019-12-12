package com.liushang.grpc;

import com.google.protobuf.ByteString;
import com.liushang.proto.*;
import io.grpc.stub.StreamObserver;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    /**
     * ???????????????????rpc????????????????????????????????
     * @param request
     * @param responseObserver
     */
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
       //???????????????????????????????????MyResponse??????return????????
        System.out.println("接收到客户端信息："+request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();


    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三1").setAge(21).setCity("武汉1").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三2").setAge(22).setCity("武汉2").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三3").setAge(23).setCity("武汉3").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三4").setAge(24).setCity("武汉4").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三5").setAge(25).setCity("武汉5").build());
        responseObserver.onCompleted();


    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWrpperByages(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            //接收客户端发过来的请求
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("onNext:"+value.getAge());

            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            /**
             * 客户端以流式的把数据进行一个一个的 发送给服务器端
             * 当全部发送完以后客户端有一个onCompleted的事件，服务器端这边会感知到这个事件
             * 感知到这个事件之后，在它的onCompleted里面完成向客户端返回最终结果
             */
            @Override
            public void onCompleted() {
                StudentResponse studentResponse1 =StudentResponse.newBuilder().setName("刘尚1").setAge(31).setCity("武汉").build();
                StudentResponse studentResponse2 =StudentResponse.newBuilder().setName("刘尚2").setAge(33).setCity("西安").build();
                StudentResponseList studentResponseList = StudentResponseList.newBuilder().
                        addStudentResponse(studentResponse1).addStudentResponse(studentResponse2).build();

                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * 双向数据传递
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());

            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
               responseObserver.onCompleted();
            }
        };
    }
}

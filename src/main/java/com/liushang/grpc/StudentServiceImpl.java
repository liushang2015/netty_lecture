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
        System.out.println("���յ��ͻ�����Ϣ��"+request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("����").build());
        responseObserver.onCompleted();


    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        responseObserver.onNext(StudentResponse.newBuilder().setName("����1").setAge(21).setCity("�人1").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("����2").setAge(22).setCity("�人2").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("����3").setAge(23).setCity("�人3").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("����4").setAge(24).setCity("�人4").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("����5").setAge(25).setCity("�人5").build());
        responseObserver.onCompleted();


    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWrpperByages(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            //���տͻ��˷�����������
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("onNext:"+value.getAge());

            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            /**
             * �ͻ�������ʽ�İ����ݽ���һ��һ���� ���͸���������
             * ��ȫ���������Ժ�ͻ�����һ��onCompleted���¼�������������߻��֪������¼�
             * ��֪������¼�֮��������onCompleted���������ͻ��˷������ս��
             */
            @Override
            public void onCompleted() {
                StudentResponse studentResponse1 =StudentResponse.newBuilder().setName("����1").setAge(31).setCity("�人").build();
                StudentResponse studentResponse2 =StudentResponse.newBuilder().setName("����2").setAge(33).setCity("����").build();
                StudentResponseList studentResponseList = StudentResponseList.newBuilder().
                        addStudentResponse(studentResponse1).addStudentResponse(studentResponse2).build();

                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * ˫�����ݴ���
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

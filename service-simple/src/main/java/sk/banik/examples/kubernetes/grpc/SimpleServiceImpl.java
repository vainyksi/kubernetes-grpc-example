package sk.banik.examples.kubernetes.grpc;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import sk.banik.examples.kubernetes.grpc.proto.SimpleServiceGrpc.SimpleServiceImplBase;
import sk.banik.examples.kubernetes.grpc.proto.SimpleServiceOuterClass.InfoResponse;

public class SimpleServiceImpl extends SimpleServiceImplBase {

    @Override
    public void getInfo(final Empty request, final StreamObserver<InfoResponse> responseObserver) {
        final InfoResponse infoResponse = InfoResponse.newBuilder()
                .setPrimaryInfo("hello world")
                .setOtherInfo("this is secondary info")
                .build();

        responseObserver.onNext(infoResponse);
        responseObserver.onCompleted();
    }
}

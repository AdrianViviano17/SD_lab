package com.franco.producto;

import io.grpc.stub.StreamObserver;

public class CalculatorService extends CalculatorGrpc.CalculatorImplBase {

    @Override
    public void sum(Request request, StreamObserver<Response> responseObserver) {
        int result = request.getA() + request.getB();

        Response response = Response.newBuilder()
                .setResult(result)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
package org.example.park.server;

import io.grpc.stub.StreamObserver;
import org.example.park.MessageServiceGrpc;
import org.example.park.MessageServiceOuterClass;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class MessageImpl extends MessageServiceGrpc.MessageServiceImplBase {
    Logger log = Logger.getLogger("MessageImpl");

    @Override
    public void sendMessage(MessageServiceOuterClass.Request request, StreamObserver<MessageServiceOuterClass.Response> responseObserver) {
        // get the request
        int id = request.getId();
        String name = request.getName();

        String logMessage = "received name: " + name + " and id: " + id + " from the client";

        log.info(logMessage);

        // create the response
        String responseMessage = "hai "+name+" we started to process your query";
        MessageServiceOuterClass.Response response = MessageServiceOuterClass.Response.newBuilder()
                .setMessage(responseMessage)
                .build();

        // send the response to client
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}

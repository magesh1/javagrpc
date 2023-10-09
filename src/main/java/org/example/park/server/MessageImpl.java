package org.example.park.server;

import io.grpc.stub.StreamObserver;
import org.example.park.MessageServiceGrpc;
import org.example.park.MessageServiceOuterClass;

import java.util.logging.Logger;

public class MessageImpl extends MessageServiceGrpc.MessageServiceImplBase {
    Logger log = Logger.getLogger("MessageImpl");

    @Override
    public void sendMessage(MessageServiceOuterClass.Request request, StreamObserver<MessageServiceOuterClass.Response> responseObserver) {
        // get the request
        int id = request.getId();
        String name = request.getName();
        MessageServiceOuterClass.Topic topic = request.getTopic();

        String logMessage = "received name: " + name + " and id: " + id + " and topic: " + topic + " from the client ";

        log.info(logMessage);

        // create the response
//        String responseMessage = "hai " + name + " we started to process your query";
        MessageServiceOuterClass.Response response = null;
        switch (topic) {
            case TOPIC_AI:
                response = generateAIMessage();
                break;
            case TOPIC_TECHNOLOGY:
                response = generateTechonologyMessage();
                break;
            case TOPIC_LANGUAGE:
                response = generateLanguageMessage();
                break;
        }
//        String responseMessage = " Artificial intelligence (AI) is the ability of a computer or a robot controlled by a computer to do tasks that are usually done by humans because they require human intelligence and discernment.";
        // send the response to client
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    private MessageServiceOuterClass.Response generateLanguageMessage() {
        String msg = "Manual update required for some Java 8 users on macOS. Get Java for desktop applications. Download Java · What is Java? Uninstall help. Happy Java User.";
        MessageServiceOuterClass.Response response = MessageServiceOuterClass.Response.newBuilder()
                .setMessage(msg)
                .build();

        return response;
    }

    private MessageServiceOuterClass.Response generateTechonologyMessage() {
        String msg = "Virtual reality (VR) is a simulated experience that employs pose tracking and 3D near-eye displays to give the user an immersive feel of a virtual world.";
        MessageServiceOuterClass.Response response = MessageServiceOuterClass.Response.newBuilder()
                .setMessage(msg)
                .build();

        return response;

    }

    private MessageServiceOuterClass.Response generateAIMessage() {
        String msg = " Artificial intelligence (AI) is the ability of a computer or a robot controlled by a computer to do tasks that are usually done by humans because they require human intelligence and discernment.";
        MessageServiceOuterClass.Response response = MessageServiceOuterClass.Response.newBuilder()
                .setMessage(msg)
                .build();

        return response;

    }
}

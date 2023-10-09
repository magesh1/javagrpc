package org.example.park.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.park.MessageServiceGrpc;
import org.example.park.MessageServiceOuterClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GrpcClient {
    private static final Logger logger = Logger.getLogger("GrpcClient");

    public static void main(String[] args) throws InterruptedException {
        // create a channel to connect to grpc server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5003)
                .usePlaintext()
                .build();

        // create a client stub
        MessageServiceGrpc.MessageServiceBlockingStub blockingStub = MessageServiceGrpc.newBlockingStub(channel);

        // create a request
        MessageServiceOuterClass.Request request = MessageServiceOuterClass.Request.newBuilder()
                .setId(1)
                .setName(getRandomName())
                .setTopic(MessageServiceOuterClass.Topic.TOPIC_AI)
                .build();

        // send the request and get the response
        MessageServiceOuterClass.Response response = blockingStub.sendMessage(request);

        // response
        logger.info("response received from the client: " + response.getMessage());
        TimeUnit.SECONDS.sleep(5);

        request = MessageServiceOuterClass.Request.newBuilder()
                .setId(2)
                .setName(getRandomName())
                .setTopic(MessageServiceOuterClass.Topic.TOPIC_TECHNOLOGY)
                .build();

        // send the request and get the response
        response = blockingStub.sendMessage(request);

        // response
        logger.info("response received from the client: " + response.getMessage());
        TimeUnit.SECONDS.sleep(5);

        request = MessageServiceOuterClass.Request.newBuilder()
                .setId(3)
                .setName(getRandomName())
                .setTopic(MessageServiceOuterClass.Topic.TOPIC_LANGUAGE)
                .build();

        // send the request and get the response
        response = blockingStub.sendMessage(request);


        // shutdown the channel
        channel.shutdown();

    }

    public static String getRandomName() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hank", "Ivy", "Jack"};
        Random random = new Random();
        int randomIndex = random.nextInt(names.length);
        return names[randomIndex];
    }

}

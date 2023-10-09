package org.example.park.server;


import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(5003)
                .addService(new MessageImpl())
                .build();

        // start the server
        server.start();

        System.out.println("grpc server started.....");

        // block the main thread to keep running the server
        server.awaitTermination();

    }
}

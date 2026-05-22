package com.ejercicio.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.logging.Logger;

public class ConversionServer {
    private static final Logger logger = Logger.getLogger(ConversionServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 8080;
        Server server = ServerBuilder.forPort(port)
                .addService(new ConverterServiceImpl())
                .build();

        server.start();
        logger.info("Servidor gRPC escuchando activamente en el puerto " + port);
        server.awaitTermination();
    }
}
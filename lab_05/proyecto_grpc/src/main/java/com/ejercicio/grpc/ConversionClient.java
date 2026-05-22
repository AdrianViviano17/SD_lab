package com.ejercicio.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ConversionClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        ConverterGrpc.ConverterBlockingStub stub = ConverterGrpc.newBlockingStub(channel);

        System.out.println("========== INICIANDO PRUEBAS DE CONVERSION gRPC ==========\n");

        // Prueba 1: Celsius a Fahrenheit (ID: 0)
        llamarServidor(stub, 25.0, 0);

        // Prueba 2: Soles a Dolares (ID: 1)
        llamarServidor(stub, 100.0, 1);

        // Prueba 3: Kilometros a Millas (ID: 2)
        llamarServidor(stub, 10.0, 2);

        // Prueba 4: Metros a Pies (ID: 3 - Extra 1)
        llamarServidor(stub, 5.0, 3);

        // Prueba 5: Litros a Galones (ID: 4 - Extra 2)
        llamarServidor(stub, 20.0, 4);

        System.out.println("==========================================================");
        channel.shutdown();
    }

    private static void llamarServidor(ConverterGrpc.ConverterBlockingStub stub, double valor, int tipoId) {
        try {
            // Pasamos el ID numérico para saltarnos la restricción del Enum rígido
            ConvertRequest request = ConvertRequest.newBuilder()
                    .setValue(valor)
                    .setTypeValue(tipoId) 
                    .build();

            ConvertResponse response = stub.convert(request);
            System.out.println("[SERVIDOR] -> " + response.getMessage());
        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo procesar la conversión: " + e.getMessage());
        }
    }
}

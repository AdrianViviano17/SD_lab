package com.ejercicio.grpc;

import io.grpc.stub.StreamObserver;

public class ConverterServiceImpl extends ConverterGrpc.ConverterImplBase {

    @Override
    public void convert(ConvertRequest request, StreamObserver<ConvertResponse> responseObserver) {
        double valorOrigen = request.getValue();
        
        // Usamos el número de operación para evitar conflictos de Enum
        int tipoOperacion = request.getTypeValue(); 
        
        double valorResultado = 0.0;
        String unidadDestino = "";

        // Evaluamos dinámicamente por ID numérico de conversión
        if (tipoOperacion == 0 || request.getType() == ConversionType.CELSIUS_TO_FAHRENHEIT) {
            valorResultado = (valorOrigen * 9 / 5) + 32;
            unidadDestino = "ºF";
        } else if (tipoOperacion == 1) {
            valorResultado = valorOrigen / 3.75; // Soles a Dólares
            unidadDestino = "USD";
        } else if (tipoOperacion == 2) {
            valorResultado = valorOrigen * 0.621371; // Kilómetros a Millas
            unidadDestino = "Millas";
        } else if (tipoOperacion == 3) {
            valorResultado = valorOrigen * 3.28084; // Metros a Pies (Extra 1)
            unidadDestino = "Pies";
        } else if (tipoOperacion == 4) {
            valorResultado = valorOrigen * 0.264172; // Litros a Galones (Extra 2)
            unidadDestino = "Galones";
        } else {
            valorResultado = valorOrigen;
            unidadDestino = "Unidades";
        }

        String mensajeFormateado = String.format("%.2f origen equivale a %.2f %s", valorOrigen, valorResultado, unidadDestino);
        
        ConvertResponse response = ConvertResponse.newBuilder()
                .setResult(valorResultado)
                .setMessage(mensajeFormateado)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
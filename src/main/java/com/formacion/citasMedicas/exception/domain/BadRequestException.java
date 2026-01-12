package com.formacion.citasMedicas.exception.domain;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String mensaje){
        super(mensaje);
    }
}

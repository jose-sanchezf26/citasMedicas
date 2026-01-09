package com.formacion.citasMedicas.exception.domain;

public class CitaNoExisteException extends RuntimeException{
    public CitaNoExisteException(Long id){
        super("La cita con id " + id + " no existe.");
    }
}

package com.formacion.citasMedicas.exception.domain;

public class CitaConDiagnosticoYaExisteException extends RuntimeException{
    public CitaConDiagnosticoYaExisteException(Long id){
        super("La cita con id " + id + " ya tiene un diagnóstico asignado");
    }
}

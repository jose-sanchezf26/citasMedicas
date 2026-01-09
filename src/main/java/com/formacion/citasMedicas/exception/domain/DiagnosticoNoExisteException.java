package com.formacion.citasMedicas.exception.domain;

import com.formacion.citasMedicas.dto.DiagnosticoResponseDTO;

public class DiagnosticoNoExisteException extends RuntimeException{
    public DiagnosticoNoExisteException(Long id){
        super("El diagnóstico con id " + id + " no existe.");
    }
}

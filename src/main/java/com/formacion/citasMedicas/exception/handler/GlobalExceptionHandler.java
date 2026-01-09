package com.formacion.citasMedicas.exception.handler;

import com.formacion.citasMedicas.exception.domain.CitaConDiagnosticoYaExisteException;
import com.formacion.citasMedicas.exception.domain.CitaNoExisteException;
import com.formacion.citasMedicas.exception.domain.DiagnosticoNoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CitaNoExisteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCitaNoExiste(CitaNoExisteException ex){
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(CitaConDiagnosticoYaExisteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleCitaConDiagostico(CitaConDiagnosticoYaExisteException ex){
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(DiagnosticoNoExisteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleDiagnosticoNoExiste(DiagnosticoNoExisteException ex){
        return Map.of("error", ex.getMessage());
    }
}

package com.formacion.citasMedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticoResponseDTO {

    private Long id;
    private String valoracionEspecialista;
    private String enfermedad;

}

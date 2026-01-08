package com.formacion.citasMedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaResponseDTO {

    private Long id;
    private LocalDateTime fechaHora;
    private String motivoCita;
    private Long pacienteId;
    private String nombrePaciente;
    private Long medicoId;
    private String nombreMedico;

}

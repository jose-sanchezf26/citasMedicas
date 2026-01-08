package com.formacion.citasMedicas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaRequestDTO {

    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime fechaHora;
    @NotBlank(message = "El motivo es obligatorio")
    private String motivoCita;
    @NotNull(message = "El paciente es obligatorio")
    private Long pacienteId;
    @NotNull(message = "El medico es obligatorio")
    private Long medicoId;

}

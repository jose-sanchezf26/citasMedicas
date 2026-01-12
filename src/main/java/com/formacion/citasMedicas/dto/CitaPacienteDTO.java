package com.formacion.citasMedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaPacienteDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private String motivoCita;
    private MedicoResumenDTO medico;
}

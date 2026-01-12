package com.formacion.citasMedicas.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitaMedicoDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private String motivoCita;
    private PacienteResumenDTO paciente;
}

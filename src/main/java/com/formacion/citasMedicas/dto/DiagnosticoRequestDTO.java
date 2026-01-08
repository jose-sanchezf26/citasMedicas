package com.formacion.citasMedicas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticoRequestDTO {

    @NotBlank
    private String valoracionEspecialista;
    @NotBlank
    private String enfermedad;
    @NotNull
    private Long citaId;
}

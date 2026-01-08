package com.formacion.citasMedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoResponseDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String numColegiado;
}

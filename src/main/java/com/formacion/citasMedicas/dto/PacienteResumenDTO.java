package com.formacion.citasMedicas.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResumenDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String nss;

}

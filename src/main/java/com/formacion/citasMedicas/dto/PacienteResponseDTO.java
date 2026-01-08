package com.formacion.citasMedicas.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String nss;
    private String numTarjeta;
    private String telefono;
    private String direccion;

}

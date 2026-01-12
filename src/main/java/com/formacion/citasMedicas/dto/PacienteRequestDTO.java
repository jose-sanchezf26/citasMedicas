package com.formacion.citasMedicas.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotBlank(message = "El usuario es obligatorio")
    private String usuario;

    @NotBlank(message = "La clave es obligatoria")
    private String clave;

    @NotBlank(message = "El NSS es obligatorio")
    private String nss;

    @NotBlank(message = "El número de tarjeta es obligatorio")
    private String numTarjeta;

    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    private List<Long> medicosIds;

}

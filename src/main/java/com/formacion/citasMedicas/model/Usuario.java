package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;

// Uso de Lombok para generar getters y setters automáticamente
@Data
@MappedSuperclass
public class Usuario {

    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;

}

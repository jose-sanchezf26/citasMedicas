package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;

// Uso de Lombok para generar getters y setters automáticamente
@Data
@Entity
@Table(name = "USUARIOS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "CLAVE")
    private String clave;

}

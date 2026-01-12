package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// Uso de Lombok para generar getters y setters automáticamente
@Getter
@Setter
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
    @Column(name = "USUARIO", unique = true)
    private String usuario;
    @Column(name = "CLAVE")
    private String clave;

}

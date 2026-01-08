package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
// Creación de la tabla pacientes
@Entity
@Table(name = "pacientes")
public class Paciente extends Usuario{

    @Id
    // Política de generación del valor del id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nss;
    private String numTarjeta;
    private String telefono;
    private String direccion;

    // Relación muchos a muchos con médicos
    @ManyToMany
    @JoinTable( name = "pacienteMedico",
                joinColumns = @JoinColumn(name = "pacienteId"),
                inverseJoinColumns = @JoinColumn(name = "medicoId"))
    private Set<Medico> medicos;

}

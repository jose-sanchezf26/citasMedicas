package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "medicos")
public class Medico extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numColegiado;

    // Relación muchos a muchos con Paciente
    @ManyToMany(mappedBy = "medicos")
    private Set<Paciente> pacientes;

}

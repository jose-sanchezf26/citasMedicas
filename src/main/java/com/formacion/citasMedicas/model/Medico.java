package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "MEDICOS")
public class Medico extends Usuario{

    @Column(name = "NUM_COLEGIADO")
    private String numColegiado;

    // Relación muchos a muchos con Paciente
    @ManyToMany(mappedBy = "medicos")
    private Set<Paciente> pacientes;

    // Relación uno a mucho con Cita
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private Set<Cita> citas;
}

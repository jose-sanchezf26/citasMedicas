package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PACIENTES")
public class Paciente extends Usuario{

    @Column(name = "NSS")
    private String nss;
    @Column(name = "NUMTARJETA")
    private String numTarjeta;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "DIRECCION")
    private String direccion;

    // Relación muchos a muchos con médicos
    @ManyToMany
    @JoinTable( name = "PACIENTE_MEDICO",
                joinColumns = @JoinColumn(name = "PACIENTE_ID"),
                inverseJoinColumns = @JoinColumn(name = "MEDICO_ID"))
    private Set<Medico> medicos;

    // Relación uno a mucho con cita
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Set<Cita> citas;

}

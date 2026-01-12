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
    private List<Medico> medicos;

    // Relación uno a mucho con cita
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Cita> citas;

}

package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DIAGNOSTICOS")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Column(name = "VALORACION_ESPECIALISTA")
    private String valoracionEspecialista;
    @Column(name = "ENFERMEDAD")
    private String enfermedad;

    @OneToOne
    @JoinColumn(name = "CITAID", referencedColumnName = "ID")
    private Cita cita;
}

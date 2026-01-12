package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "CITAS")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FECHA_HORA")
    private LocalDateTime fechaHora;
    @Column(name = "MOTIVO_CITA")
    private String motivoCita;

    // Relación con Paciente
    @ManyToOne
    @JoinColumn(name = "PACIENTE_ID", nullable = false)
    private Paciente paciente;

    // Relación con Medico
    @ManyToOne
    @JoinColumn(name = "MEDICO_ID", nullable = false)
    private Medico medico;

    // Relación con Diagnóstico
    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    private Diagnostico diagnostico;
}

package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;
    private String motivoCita;

    // Relación con Paciente
    @ManyToOne
    @JoinColumn(name = "pacienteId", nullable = false)
    private Paciente paciente;

    // Relación con Medico
    @ManyToOne
    @JoinColumn(name = "medicoId", nullable = false)
    private Medico medico;

    // Relación con Diagnóstico
    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private Diagnostico diagnostico;
}

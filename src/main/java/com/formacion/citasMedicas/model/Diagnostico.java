package com.formacion.citasMedicas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "diagnosticos")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String valoracionEspecialista;
    private String enfermedad;
}

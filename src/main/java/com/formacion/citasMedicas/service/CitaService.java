package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.CitaRequestDTO;
import com.formacion.citasMedicas.dto.CitaResponseDTO;
import com.formacion.citasMedicas.model.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<CitaResponseDTO> listarCitas();
    CitaResponseDTO obtenerCita(Long id);
    CitaResponseDTO crearCita(CitaRequestDTO citaDTO);
    CitaResponseDTO actualizarCita(Long id, CitaRequestDTO citaDTO);
    void eliminarCita(Long id);
    Cita comprobarCita(Long id);
}

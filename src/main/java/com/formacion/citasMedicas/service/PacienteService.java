package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.PacienteRequestDTO;
import com.formacion.citasMedicas.dto.PacienteResponseDTO;
import com.formacion.citasMedicas.model.Paciente;

import java.util.List;

public interface PacienteService {
    List<PacienteResponseDTO> listarPacientes();
    PacienteResponseDTO obtenerPaciente(Long id);
    PacienteResponseDTO crearPaciente(PacienteRequestDTO PacienteDTO);
    PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO PacienteDTO);
    void eliminarPaciente(Long id);
    Paciente comprobarPaciente(Long id);
}

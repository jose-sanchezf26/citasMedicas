package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.DiagnosticoRequestDTO;
import com.formacion.citasMedicas.dto.DiagnosticoResponseDTO;

import java.util.List;

public interface DiagnosticoService {
    List<DiagnosticoResponseDTO> listarDiagnosticos();
    DiagnosticoResponseDTO obtenerDiagnostico(Long id);
    DiagnosticoResponseDTO crearDiagnostico(DiagnosticoRequestDTO diagnosticoDTO);
    DiagnosticoResponseDTO actualizarDiagnostico(Long id, DiagnosticoRequestDTO diagnosticoDTO);
    void eliminarDiagnostico(Long id);
}

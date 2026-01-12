package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.MedicoRequestDTO;
import com.formacion.citasMedicas.dto.MedicoResponseDTO;
import com.formacion.citasMedicas.model.Medico;

import java.util.List;

public interface MedicoService {
    List<MedicoResponseDTO> listarMedicos();
    MedicoResponseDTO obtenerMedico(Long id);
    MedicoResponseDTO crearMedico(MedicoRequestDTO medicoDTO);
    MedicoResponseDTO actualizarMedico(Long id, MedicoRequestDTO medicoDTO);
    void eliminarMedico(Long id);
    Medico comprobarMedico(Long id);
}

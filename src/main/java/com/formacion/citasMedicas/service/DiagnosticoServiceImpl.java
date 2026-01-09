package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.DiagnosticoRequestDTO;
import com.formacion.citasMedicas.dto.DiagnosticoResponseDTO;
import com.formacion.citasMedicas.exception.domain.CitaConDiagnosticoYaExisteException;
import com.formacion.citasMedicas.exception.domain.CitaNoExisteException;
import com.formacion.citasMedicas.exception.domain.DiagnosticoNoExisteException;
import com.formacion.citasMedicas.mapper.DiagnosticoMapper;
import com.formacion.citasMedicas.model.Cita;
import com.formacion.citasMedicas.model.Diagnostico;
import com.formacion.citasMedicas.repository.CitaRepository;
import com.formacion.citasMedicas.repository.DiagnosticoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosticoServiceImpl implements DiagnosticoService {

    private final DiagnosticoRepository repository;
    private final CitaRepository citaRepository;
    private final DiagnosticoMapper mapper;

    public List<DiagnosticoResponseDTO> listarDiagnosticos(){
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public DiagnosticoResponseDTO obtenerDiagnostico(Long id){
        Diagnostico diagnostico = repository.findById(id)
                .orElseThrow(() -> new DiagnosticoNoExisteException(id));
        return mapper.toResponse(diagnostico);
    }

    public DiagnosticoResponseDTO crearDiagnostico(DiagnosticoRequestDTO diagnosticoDTO){
        Diagnostico diagnostico = mapper.toEntity(diagnosticoDTO);

        // Comprueba que la cita existe
        Long citaId = diagnosticoDTO.getCitaId();
        citaRepository.findById(citaId)
                .orElseThrow(() -> new CitaNoExisteException(citaId));

        // Comprueba que no exista un diagnóstico asociado a la cita
        if (repository.existsByCitaId(citaId)) throw new CitaConDiagnosticoYaExisteException(citaId);

        // Envía el DTO de salida
        return mapper.toResponse(repository.save(diagnostico));
    }

    public DiagnosticoResponseDTO actualizarDiagnostico(Long id, DiagnosticoRequestDTO diagnosticoDTO){
        // Trae el diagnostico y comprueba que existe
        Diagnostico diagnostico = repository.findById(id)
                .orElseThrow(() -> new DiagnosticoNoExisteException(id));

        // Comprueba que la cita existe
        Long citaId = diagnosticoDTO.getCitaId();
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new CitaNoExisteException(citaId));

        // Comprueba que no exista un diagnóstico asociado a la cita con Id distinto al proporcionado
        if (repository.existsByCitaIdAndIdNot(citaId, id)) throw new CitaConDiagnosticoYaExisteException(citaId);

        // Actualiza los campos y establece la cita
        mapper.updateDiagnosticoFromDTO(diagnosticoDTO, diagnostico);
        diagnostico.setCita(cita);

        // Envía el DTO de salida
        return mapper.toResponse(diagnostico);
    }

    public void eliminarDiagnostico(Long id){
        Diagnostico diagnostico = repository.findById(id)
                .orElseThrow(() -> new DiagnosticoNoExisteException(id));
        repository.delete(diagnostico);
    }
}
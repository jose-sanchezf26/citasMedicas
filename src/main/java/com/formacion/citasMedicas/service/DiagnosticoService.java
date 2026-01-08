package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.DiagnosticoRequestDTO;
import com.formacion.citasMedicas.dto.DiagnosticoResponseDTO;
import com.formacion.citasMedicas.mapper.DiagnosticoMapper;
import com.formacion.citasMedicas.model.Cita;
import com.formacion.citasMedicas.model.Diagnostico;
import com.formacion.citasMedicas.repository.CitaRepository;
import com.formacion.citasMedicas.repository.DiagnosticoRepository;
import jdk.jshell.Diag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiagnosticoService {

    private final DiagnosticoRepository repository;
    private final CitaRepository citaRepository;
    private final DiagnosticoMapper mapper;

    public List<DiagnosticoResponseDTO> listarDiagnosticos(){
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Optional<DiagnosticoResponseDTO> obtenerDiagnostico(Long id){
        return repository.findById(id)
                .map(mapper::toResponse);
    }

    public DiagnosticoResponseDTO crearDiagnostico(DiagnosticoRequestDTO diagnosticoDTO){
        Diagnostico diagnostico = mapper.toEntity(diagnosticoDTO);

        // Validación de que la cita existe
        Long citaId = diagnosticoDTO.getCitaId();
        Cita cita = citaRepository.findById(citaId).orElse(null);
        if (cita == null) return null;

        // Validación de que la cita indicada no tiene ya un diagnóstico asignado
        if (repository.existsByCitaId(citaId)) return null;

        diagnostico.setCita(cita);

        return mapper.toResponse(repository.save(diagnostico));
    }

    public Optional<DiagnosticoResponseDTO> actualizarDiagnostico(Long id, DiagnosticoRequestDTO diagnosticoDTO){
        return repository.findById(id)
                .map(diagnostico -> {
                    Cita cita = citaRepository.findById(diagnosticoDTO.getCitaId()).orElse(null);
                    if (cita == null) return null;
                    mapper.updateDiagnosticoFromDTO(diagnosticoDTO, diagnostico);
                    diagnostico.setCita(cita);
                    return mapper.toResponse(repository.save(diagnostico));
                });
    }

    public boolean eliminarDiagnostico(Long id){
        return repository.findById(id).map(diagnostico -> {
            diagnostico.setCita(null);
            repository.save(diagnostico);
            repository.delete(diagnostico);
            return true;
        }).orElse(false);
    }
}


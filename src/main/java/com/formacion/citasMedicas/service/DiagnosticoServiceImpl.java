package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.DiagnosticoRequestDTO;
import com.formacion.citasMedicas.dto.DiagnosticoResponseDTO;
import com.formacion.citasMedicas.exception.domain.BadRequestException;
import com.formacion.citasMedicas.exception.domain.NotFoundException;
import com.formacion.citasMedicas.mapper.DiagnosticoMapper;
import com.formacion.citasMedicas.model.Cita;
import com.formacion.citasMedicas.model.Diagnostico;
import com.formacion.citasMedicas.repository.DiagnosticoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosticoServiceImpl implements DiagnosticoService {

    private final DiagnosticoRepository repository;
    private final CitaService citaService;
    private final DiagnosticoMapper mapper;

    @Override
    public List<DiagnosticoResponseDTO> listarDiagnosticos(){
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public DiagnosticoResponseDTO obtenerDiagnostico(Long id){
        Diagnostico diagnostico = comprobarDiagnostico(id);
        return mapper.toResponse(diagnostico);
    }

    @Override
    public DiagnosticoResponseDTO crearDiagnostico(DiagnosticoRequestDTO diagnosticoDTO){
        Diagnostico diagnostico = mapper.toEntity(diagnosticoDTO);

        // Comprueba que la cita existe
        Long citaId = diagnosticoDTO.getCitaId();
        Cita cita = citaService.comprobarCita(citaId);

        // Comprueba que no exista un diagnóstico asociado a la cita
        if (repository.existsByCitaId(citaId)) throw new BadRequestException("La cita con id " + citaId + " ya tiene un diagnostico asignado");

        // Establece la cita para el diagnostico
        diagnostico.setCita(cita);

        // Envía el DTO de salida
        return mapper.toResponse(repository.save(diagnostico));
    }

    @Override
    public DiagnosticoResponseDTO actualizarDiagnostico(Long id, DiagnosticoRequestDTO diagnosticoDTO){
        // Trae el diagnostico y comprueba que existe
        Diagnostico diagnostico = comprobarDiagnostico(id);

        // Comprueba que la cita existe
        Long citaId = diagnosticoDTO.getCitaId();
        Cita cita = citaService.comprobarCita(citaId);

        // Comprueba que no exista un diagnóstico asociado a la cita con Id distinto al proporcionado
        if (repository.existsByCitaIdAndIdNot(citaId, id)) throw new BadRequestException("La cita con id " + citaId + " ya tiene un diagnostico asignado");

        // Actualiza los campos y establece la cita
        mapper.updateDiagnosticoFromDTO(diagnosticoDTO, diagnostico);
        diagnostico.setCita(cita);

        // Envía el DTO de salida
        return mapper.toResponse(repository.save(diagnostico));
    }

    @Override
    public void eliminarDiagnostico(Long id){
        Diagnostico diagnostico = comprobarDiagnostico(id);
        repository.delete(diagnostico);
    }

    @Override
    public Diagnostico comprobarDiagnostico(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Diagnostico con id " + id + " no existe."));
    }
}
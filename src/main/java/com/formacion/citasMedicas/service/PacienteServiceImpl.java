package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.PacienteRequestDTO;
import com.formacion.citasMedicas.dto.PacienteResponseDTO;
import com.formacion.citasMedicas.exception.domain.NotFoundException;
import com.formacion.citasMedicas.mapper.PacienteMapper;
import com.formacion.citasMedicas.model.Paciente;
import com.formacion.citasMedicas.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    // Obtener todos los pacientes
    @Override
    public List<PacienteResponseDTO> listarPacientes(){
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    // Obtener un paciente por ID
    @Override
    public PacienteResponseDTO obtenerPaciente(Long id){
        Paciente paciente = comprobarPaciente(id);
        return mapper.toResponse(paciente);
    }

    // Guardar un paciente
    @Override
    public PacienteResponseDTO crearPaciente(PacienteRequestDTO pacienteDTO) {
        // Convierte el DTO en entidad
        Paciente paciente = mapper.toEntity(pacienteDTO);
        // Guarda al paciente
        Paciente guardado = repository.save(paciente);
        // Devuelve al controlador la entidad ya mapeada a DTO
        return mapper.toResponse(guardado);
    }

    // Actualizar un paciente
    @Override
    public PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO pacienteDTO){
        Paciente paciente = comprobarPaciente(id);

        mapper.updatePacienteFromDTO(pacienteDTO, paciente);

        return mapper.toResponse(repository.save(paciente));
    }

    // Eliminar un paciente
    @Override
    public void eliminarPaciente(Long id){
        comprobarPaciente(id);
        repository.deleteById(id);
    }

    @Override
    public Paciente comprobarPaciente(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente con id " + id + " no existe."));
    }
}

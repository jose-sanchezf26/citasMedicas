package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.PacienteRequestDTO;
import com.formacion.citasMedicas.dto.PacienteResponseDTO;
import com.formacion.citasMedicas.mapper.PacienteMapper;
import com.formacion.citasMedicas.model.Paciente;
import com.formacion.citasMedicas.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    public PacienteService(PacienteRepository repository, PacienteMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    // Obtener todos los pacientes
    public List<PacienteResponseDTO> listarPacientes(){
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    // Obtener un paciente por ID
    public Optional<PacienteResponseDTO> obtenerPaciente(Long id){
        return repository.findById(id)
                .map(mapper::toResponse);
    }

    // Guardar un paciente
    public PacienteResponseDTO crearPaciente(PacienteRequestDTO pacienteDTO) {
        // Convierte el DTO en entidad
        Paciente paciente = mapper.toEntity(pacienteDTO);
        // Guarda al paciente
        Paciente guardado = repository.save(paciente);
        // Devuelve al controlador la entidad ya mapeada a DTO
        return mapper.toResponse(guardado);
    }

    // Actualizar un paciente
    public Optional<PacienteResponseDTO> actualizarPaciente(Long id, PacienteRequestDTO pacienteDTO){
        return repository.findById(id)
                .map(paciente -> {
                    mapper.updatePacienteFromDTO(pacienteDTO, paciente);
                    Paciente guardado = repository.save(paciente);
                    return mapper.toResponse(guardado);
                });
    }

    // Eliminar un paciente
    public boolean eliminarPaciente(Long id){
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

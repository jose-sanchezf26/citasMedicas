package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.PacienteRequestDTO;
import com.formacion.citasMedicas.dto.PacienteResponseDTO;
import com.formacion.citasMedicas.dto.PacienteResumenDTO;
import com.formacion.citasMedicas.exception.domain.NotFoundException;
import com.formacion.citasMedicas.mapper.PacienteMapper;
import com.formacion.citasMedicas.model.Paciente;
import com.formacion.citasMedicas.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;
    private final MedicoService medicoService;
    private final UsuarioService usuarioService;

    // Obtener todos los pacientes
    @Override
    public List<PacienteResumenDTO> listarPacientes(){
        return repository.findAll().stream()
                .map(mapper::toResumenResponse)
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

        // Valida el nombre de usuario
        usuarioService.existeNombreUsuario(paciente.getUsuario());

        // Valida y establece los medicos
        paciente.setMedicos(medicoService.obtenerMedicos(pacienteDTO.getMedicosIds()));

        // Devuelve al controlador la entidad ya mapeada a DTO
        return mapper.toResponse(repository.save(paciente));
    }

    // Actualizar un paciente
    @Override
    public PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO pacienteDTO){
        Paciente paciente = comprobarPaciente(id);

        // Valida el nombre de usuario, teniendo en cuenta su id
        usuarioService.existeNombreUsuarioConID(id, paciente.getUsuario());

        // Comprueba la lista de médicos y la actualiza
        paciente.setMedicos(medicoService.obtenerMedicos(pacienteDTO.getMedicosIds()));

        // Actualiza los demás campos mediante mapper
        mapper.updatePacienteFromDTO(pacienteDTO, paciente);

        // Guarda los cambios
        return mapper.toResponse(repository.save(paciente));
    }

    // Eliminar un paciente
    @Override
    public void eliminarPaciente(Long id){
        // Comprueba que existe
        comprobarPaciente(id);
        repository.deleteById(id);
    }

    @Override
    public Paciente comprobarPaciente(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente con id " + id + " no existe."));
    }
}

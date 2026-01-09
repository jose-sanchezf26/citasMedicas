package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.CitaRequestDTO;
import com.formacion.citasMedicas.dto.CitaResponseDTO;
import com.formacion.citasMedicas.mapper.CitaMapper;
import com.formacion.citasMedicas.model.Cita;
import com.formacion.citasMedicas.model.Medico;
import com.formacion.citasMedicas.model.Paciente;
import com.formacion.citasMedicas.repository.CitaRepository;
import com.formacion.citasMedicas.repository.MedicoRepository;
import com.formacion.citasMedicas.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final CitaMapper mapper;

    public List<CitaResponseDTO> listarCitas(){
        return citaRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Optional<CitaResponseDTO> obtenerCita(Long id){
        return citaRepository.findById(id)
                .map(mapper::toResponse);
    }

    public CitaResponseDTO crearCita(CitaRequestDTO citaDTO){
        Cita cita = mapper.toEntity(citaDTO);

        // Validación de la entidad, se busca que existan el paciente y el médico
        Paciente paciente = comprobarPaciente(citaDTO.getPacienteId());
        Medico medico = comprobarMedico(citaDTO.getMedicoId());

        if (medico == null || paciente == null) return null;

        cita.setPaciente(paciente);
        cita.setMedico(medico);

        return mapper.toResponse(citaRepository.save(cita));
    }

    public Optional<CitaResponseDTO> actualizarCita(Long id, CitaRequestDTO citaDTO){
        return citaRepository.findById(id)
                .map(cita -> {
                    mapper.updateCitaFromDTO(citaDTO, cita);
                    Paciente paciente = comprobarPaciente(citaDTO.getPacienteId());
                    Medico medico = comprobarMedico(citaDTO.getMedicoId());
                    if (medico == null || paciente == null) return null;
                    cita.setPaciente(paciente);
                    cita.setMedico(medico);
                    return mapper.toResponse(citaRepository.save(cita));
                });
    }

    public boolean eliminarCita(Long id){
        if(citaRepository.existsById(id)){
            citaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Paciente comprobarPaciente(Long pacienteId){
        return pacienteRepository.findById(pacienteId).orElse(null);
    }

    private Medico comprobarMedico(Long medicoId){
        return medicoRepository.findById(medicoId).orElse(null);
    }
}

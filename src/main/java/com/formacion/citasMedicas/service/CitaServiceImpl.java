package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.CitaRequestDTO;
import com.formacion.citasMedicas.dto.CitaResponseDTO;
import com.formacion.citasMedicas.exception.domain.NotFoundException;
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
public class CitaServiceImpl  implements CitaService{

    private final CitaRepository citaRepository;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final CitaMapper mapper;

    @Override
    public List<CitaResponseDTO> listarCitas(){
        return citaRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public CitaResponseDTO obtenerCita(Long id){
        Cita cita = comprobarCita(id);
        return mapper.toResponse(cita);
    }

    @Override
    public CitaResponseDTO crearCita(CitaRequestDTO citaDTO){
        Cita cita = mapper.toEntity(citaDTO);

        // Validación de la entidad, se busca que existan el paciente y el médico
        Paciente paciente = pacienteService.comprobarPaciente(citaDTO.getPacienteId());
        Medico medico = medicoService.comprobarMedico(citaDTO.getMedicoId());

        cita.setPaciente(paciente);
        cita.setMedico(medico);

        return mapper.toResponse(citaRepository.save(cita));
    }

    @Override
    public CitaResponseDTO actualizarCita(Long id, CitaRequestDTO citaDTO){
        Cita cita = comprobarCita(id);

        // Validación de la entidad, se busca que existan el paciente y el médico
        Paciente paciente = pacienteService.comprobarPaciente(citaDTO.getPacienteId());
        Medico medico = medicoService.comprobarMedico(citaDTO.getMedicoId());

        mapper.updateCitaFromDTO(citaDTO, cita);
        cita.setPaciente(paciente);
        cita.setMedico(medico);

        return mapper.toResponse(citaRepository.save(cita));
    }

    @Override
    public void eliminarCita(Long id){
        Cita cita = comprobarCita(id);
        citaRepository.delete(cita);
    }

    @Override
    public Cita comprobarCita(Long id){
        return citaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cita con id " + id + " no existe."));
    }
}

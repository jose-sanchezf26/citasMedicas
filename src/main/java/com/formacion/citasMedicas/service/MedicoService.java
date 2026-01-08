package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.MedicoRequestDTO;
import com.formacion.citasMedicas.dto.MedicoResponseDTO;
import com.formacion.citasMedicas.mapper.MedicoMapper;
import com.formacion.citasMedicas.model.Medico;
import com.formacion.citasMedicas.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    private final MedicoRepository repository;
    private final MedicoMapper mapper;

    public MedicoService(MedicoRepository repository, MedicoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MedicoResponseDTO> listarMedicos(){
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Optional<MedicoResponseDTO> obtenerMedico(Long id){
        return repository.findById(id)
                .map(mapper::toResponse);
    }

    public MedicoResponseDTO crearMedico(MedicoRequestDTO medicoDTO){
        Medico medico = mapper.toEntity(medicoDTO);
        Medico guardado = repository.save(medico);
        return mapper.toResponse(guardado);
    }

    public Optional<MedicoResponseDTO> actualizarMedico(Long id, MedicoRequestDTO medicoDTO){
        return repository.findById(id)
                .map(medico -> {
                    mapper.updateMedicoFromDTO(medicoDTO, medico);
                    Medico guardado = repository.save(medico);
                    return mapper.toResponse(guardado);
                });
    }

    public boolean eliminarMedico(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

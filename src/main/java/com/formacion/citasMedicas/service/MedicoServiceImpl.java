package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.MedicoRequestDTO;
import com.formacion.citasMedicas.dto.MedicoResponseDTO;
import com.formacion.citasMedicas.exception.domain.NotFoundException;
import com.formacion.citasMedicas.mapper.MedicoMapper;
import com.formacion.citasMedicas.model.Medico;
import com.formacion.citasMedicas.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService{

    private final MedicoRepository repository;
    private final MedicoMapper mapper;

    @Override
    public List<MedicoResponseDTO> listarMedicos(){
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public MedicoResponseDTO obtenerMedico(Long id){
        Medico medico = comprobarMedico(id);
        return mapper.toResponse(medico);
    }

    @Override
    public MedicoResponseDTO crearMedico(MedicoRequestDTO medicoDTO){
        Medico medico = mapper.toEntity(medicoDTO);

        Medico guardado = repository.save(medico);

        return mapper.toResponse(guardado);
    }

    @Override
    public MedicoResponseDTO actualizarMedico(Long id, MedicoRequestDTO medicoDTO){
        Medico medico = comprobarMedico(id);

        mapper.updateMedicoFromDTO(medicoDTO, medico);

        return mapper.toResponse(repository.save(medico));
    }

    @Override
    public void eliminarMedico(Long id){
        comprobarMedico(id);
        repository.deleteById(id);
    }

    @Override
    public Medico comprobarMedico(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Medico con id " + id + " no existe."));
    }
}

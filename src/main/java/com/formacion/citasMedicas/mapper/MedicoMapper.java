package com.formacion.citasMedicas.mapper;

import com.formacion.citasMedicas.dto.MedicoRequestDTO;
import com.formacion.citasMedicas.dto.MedicoResponseDTO;
import com.formacion.citasMedicas.dto.MedicoResumenDTO;
import com.formacion.citasMedicas.model.Medico;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    Medico toEntity(MedicoRequestDTO medicoDTO);
    MedicoResponseDTO toResponse(Medico medico);
    MedicoResumenDTO toResumenResponse(Medico medico);
    void updateMedicoFromDTO(MedicoRequestDTO medicoDTO, @MappingTarget Medico medico);
}

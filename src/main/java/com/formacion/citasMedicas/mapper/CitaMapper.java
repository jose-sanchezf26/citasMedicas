package com.formacion.citasMedicas.mapper;

import com.formacion.citasMedicas.dto.CitaMedicoDTO;
import com.formacion.citasMedicas.dto.CitaPacienteDTO;
import com.formacion.citasMedicas.dto.CitaRequestDTO;
import com.formacion.citasMedicas.dto.CitaResponseDTO;
import com.formacion.citasMedicas.model.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    Cita toEntity(CitaRequestDTO citaDTO);
    CitaResponseDTO toResponse(Cita cita);
    CitaMedicoDTO toCitaMedicoDTO(Cita cita);
    CitaPacienteDTO toCitaPacienteDTO(Cita cita);
    @Mapping(target = "diagnostico", ignore = true)
    void updateCitaFromDTO(CitaRequestDTO citaDTO, @MappingTarget Cita cita);
}

package com.formacion.citasMedicas.mapper;

import com.formacion.citasMedicas.dto.PacienteRequestDTO;
import com.formacion.citasMedicas.dto.PacienteResponseDTO;
import com.formacion.citasMedicas.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    @Mapping(target = "medicos", ignore = true)
    @Mapping(target = "citas", ignore = true)
    Paciente toEntity(PacienteRequestDTO dto);
    PacienteResponseDTO toResponse(Paciente paciente);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicos", ignore = true)
    @Mapping(target = "citas", ignore = true)
    void updatePacienteFromDTO(PacienteRequestDTO pacienteDTO, @MappingTarget Paciente paciente);
}

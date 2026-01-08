package com.formacion.citasMedicas.mapper;

import com.formacion.citasMedicas.dto.PacienteRequestDTO;
import com.formacion.citasMedicas.dto.PacienteResponseDTO;
import com.formacion.citasMedicas.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    Paciente toEntity(PacienteRequestDTO dto);
    PacienteResponseDTO toResponse(Paciente paciente);
    void updatePacienteFromDTO(PacienteRequestDTO pacienteDTO, @MappingTarget Paciente paciente);
}

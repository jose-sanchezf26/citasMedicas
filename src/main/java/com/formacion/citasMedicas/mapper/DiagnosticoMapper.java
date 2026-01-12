package com.formacion.citasMedicas.mapper;

import com.formacion.citasMedicas.dto.DiagnosticoRequestDTO;
import com.formacion.citasMedicas.dto.DiagnosticoResponseDTO;
import com.formacion.citasMedicas.model.Diagnostico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DiagnosticoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cita", ignore = true)
    Diagnostico toEntity(DiagnosticoRequestDTO diagnosticoDTO);

    DiagnosticoResponseDTO toResponse(Diagnostico diagnostico);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cita", ignore = true)
    void updateDiagnosticoFromDTO(DiagnosticoRequestDTO diagnosticoDTO, @MappingTarget Diagnostico diagnostico);
}

package com.formacion.citasMedicas.mapper;

import com.formacion.citasMedicas.dto.CitaRequestDTO;
import com.formacion.citasMedicas.dto.CitaResponseDTO;
import com.formacion.citasMedicas.model.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    Cita toEntity(CitaRequestDTO citaDTO);

    @Mapping(target = "nombrePaciente", expression = "java(cita.getPaciente().getNombre() + ' ' + cita.getPaciente().getApellidos())")
    @Mapping(target = "nombreMedico", expression = "java(cita.getMedico().getNombre() + ' ' + cita.getMedico().getApellidos())")
    @Mapping(target = "pacienteId", expression = "java(cita.getPaciente().getId())")
    @Mapping(target = "medicoId", expression = "java(cita.getMedico().getId())")
    @Mapping(source = "diagnostico.enfermedad", target = "diagnosticoEnfermedad")
    CitaResponseDTO toResponse(Cita cita);
    @Mapping(target = "diagnostico", ignore = true)
    void updateCitaFromDTO(CitaRequestDTO citaDTO, @MappingTarget Cita cita);
}

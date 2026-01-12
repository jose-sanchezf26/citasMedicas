package com.formacion.citasMedicas.mapper;

import com.formacion.citasMedicas.dto.UsuarioRequestDTO;
import com.formacion.citasMedicas.dto.UsuarioResponseDTO;
import com.formacion.citasMedicas.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioRequestDTO usuarioDTO);
    UsuarioResponseDTO toResponse(Usuario usuario);
    void updateUsuarioFromDTO(UsuarioRequestDTO usuarioDTO, @MappingTarget Usuario usuario);
}

package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.UsuarioRequestDTO;
import com.formacion.citasMedicas.dto.UsuarioResponseDTO;
import com.formacion.citasMedicas.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO obtenerUsuario(Long id);
    UsuarioResponseDTO crearUsuario(UsuarioRequestDTO usuarioDTO);
    UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioDTO);
    void eliminarUsuario(Long id);
    Usuario comprobarUsuario(Long id);
}

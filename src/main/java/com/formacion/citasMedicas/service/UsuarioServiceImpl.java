package com.formacion.citasMedicas.service;

import com.formacion.citasMedicas.dto.UsuarioRequestDTO;
import com.formacion.citasMedicas.dto.UsuarioResponseDTO;
import com.formacion.citasMedicas.exception.domain.BadRequestException;
import com.formacion.citasMedicas.exception.domain.NotFoundException;
import com.formacion.citasMedicas.mapper.UsuarioMapper;
import com.formacion.citasMedicas.model.Usuario;
import com.formacion.citasMedicas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public UsuarioResponseDTO obtenerUsuario(Long id) {
        Usuario usuario = comprobarUsuario(id);
        return mapper.toResponse(usuario);
    }

    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = mapper.toEntity(usuarioDTO);

        String nombreUsuario = usuario.getUsuario();
        if(repository.existsByUsuario(nombreUsuario))
            throw new BadRequestException("El nombre de usuario " + nombreUsuario + " ya existe");

        return mapper.toResponse(repository.save(usuario));
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = comprobarUsuario(id);

        String nombreUsuario = usuario.getUsuario();
        if(repository.existsByUsuarioAndIdNot(id, nombreUsuario))
            throw new BadRequestException("El nombre de usuario " + nombreUsuario + " ya existe");

        mapper.updateUsuarioFromDTO(usuarioDTO, usuario);

        return mapper.toResponse(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        comprobarUsuario(id);
        repository.deleteById(id);
    }

    @Override
    public Usuario comprobarUsuario(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario con id " + id + " no existe."));
    }
}

package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.UsuarioRequestDTO;
import com.formacion.citasMedicas.dto.UsuarioResponseDTO;
import com.formacion.citasMedicas.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios() {
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO obtenerUsuario(@PathVariable Long id){
        return service.obtenerUsuario(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO crearUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDTO){
        return service.crearUsuario(usuarioDTO);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizarUsuario(@PathVariable Long id,
                                                        @Valid @RequestBody UsuarioRequestDTO usuarioDTO){
        return service.actualizarUsuario(id, usuarioDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id){
        service.eliminarUsuario(id);
    }

}

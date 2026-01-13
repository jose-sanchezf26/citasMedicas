package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.UsuarioRequestDTO;
import com.formacion.citasMedicas.dto.UsuarioResponseDTO;
import com.formacion.citasMedicas.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Usuarios")
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios() {
        return service.listarUsuarios();
    }

    @Operation(summary = "Obtener un usuario mediante su id")
    @GetMapping("/{id}")
    public UsuarioResponseDTO obtenerUsuario(@PathVariable Long id){
        return service.obtenerUsuario(id);
    }

    @Operation(summary = "Crear un usuario")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO crearUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDTO){
        return service.crearUsuario(usuarioDTO);
    }

    @Operation(summary = "Actualizar un usuario mediante datos nuevos e id")
    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizarUsuario(@PathVariable Long id,
                                                        @Valid @RequestBody UsuarioRequestDTO usuarioDTO){
        return service.actualizarUsuario(id, usuarioDTO);
    }

    @Operation(summary = "Eliminar un usuario mediante su id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id){
        service.eliminarUsuario(id);
    }

}

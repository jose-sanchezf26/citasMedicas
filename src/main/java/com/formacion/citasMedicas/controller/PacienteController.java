package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.PacienteRequestDTO;
import com.formacion.citasMedicas.dto.PacienteResponseDTO;
import com.formacion.citasMedicas.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    // Respuesta a la solicitud GET
    @GetMapping
    public List<PacienteResponseDTO> listarPacientes(){
        return service.listarPacientes();
    }

    // Respuesta a la solicitud GET con un id
    @GetMapping("/{id}")
    public PacienteResponseDTO obtenerPaciente(@PathVariable Long id){
        return service.obtenerPaciente(id);
    }

    // Respuesta a la solicitud POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponseDTO crearPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequestDTO){
        return service.crearPaciente(pacienteRequestDTO);
    }

    // Respuesta a PUT
    @PutMapping("/{id}")
    public PacienteResponseDTO actualizarPaciente(@PathVariable Long id,
                                                       @Valid @RequestBody PacienteRequestDTO pacienteDTO){
        return service.actualizarPaciente(id, pacienteDTO);
    }

    // Respuesta a DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPaciente(@PathVariable Long id){
        service.eliminarPaciente(id);
    }
}

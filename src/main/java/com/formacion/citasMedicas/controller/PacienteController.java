package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.PacienteRequestDTO;
import com.formacion.citasMedicas.dto.PacienteResponseDTO;
import com.formacion.citasMedicas.dto.PacienteResumenDTO;
import com.formacion.citasMedicas.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Pacientes")
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    // Respuesta a la solicitud GET
    @Operation(summary = "Obtener todos los pacientes resumidos")
    @GetMapping
    public List<PacienteResumenDTO> listarPacientes(){
        return service.listarPacientes();
    }

    // Respuesta a la solicitud GET con un id
    @Operation(summary = "Obtener un paciente mediante su id")
    @GetMapping("/{id}")
    public PacienteResponseDTO obtenerPaciente(@PathVariable Long id){
        return service.obtenerPaciente(id);
    }

    // Respuesta a la solicitud POST
    @Operation(summary = "Crear un paciente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponseDTO crearPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequestDTO){
        return service.crearPaciente(pacienteRequestDTO);
    }

    // Respuesta a PUT
    @Operation(summary = "Actualizar un paciente mediante datos nuevos e id")
    @PutMapping("/{id}")
    public PacienteResponseDTO actualizarPaciente(@PathVariable Long id,
                                                       @Valid @RequestBody PacienteRequestDTO pacienteDTO){
        return service.actualizarPaciente(id, pacienteDTO);
    }

    // Respuesta a DELETE
    @Operation(summary = "Eliminar un paciente mediante su id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPaciente(@PathVariable Long id){
        service.eliminarPaciente(id);
    }
}

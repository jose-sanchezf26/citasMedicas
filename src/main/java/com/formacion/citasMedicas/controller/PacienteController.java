package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.PacienteRequestDTO;
import com.formacion.citasMedicas.dto.PacienteResponseDTO;
import com.formacion.citasMedicas.mapper.PacienteMapper;
import com.formacion.citasMedicas.model.Paciente;
import com.formacion.citasMedicas.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service){
        this.service = service;
    }

    // Respuesta a la solicitud GET
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarPacientes(){
        List<PacienteResponseDTO> pacientes = service.listarPacientes();
        return ResponseEntity.ok(pacientes);
    }

    // Respuesta a la solicitud GET con un id
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> obtenerPaciente(@PathVariable Long id){
        return service.obtenerPaciente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Respuesta a la solicitud POST
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> crearPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequestDTO){
        // Obtiene el DTO de respuesta del servicio
        PacienteResponseDTO pacienteResponseDTO = service.crearPaciente(pacienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponseDTO);
    }

    // Respuesta a PUT
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> actualizarPaciente(@PathVariable Long id,
                                                       @Valid @RequestBody PacienteRequestDTO pacienteDTO){
        return service.actualizarPaciente(id, pacienteDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Respuesta a DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id){
        boolean eliminado = service.eliminarPaciente(id);
        if (eliminado)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}

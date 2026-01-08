package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.CitaRequestDTO;
import com.formacion.citasMedicas.dto.CitaResponseDTO;
import com.formacion.citasMedicas.service.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/citas")
public class CitaController {

    private final CitaService service;

    @GetMapping
    public ResponseEntity<List<CitaResponseDTO>> listarCitas(){
        return ResponseEntity.ok(service.listarCitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponseDTO> obtenerCita(@PathVariable Long id){
        return service.obtenerCita(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CitaResponseDTO> crearCita(@Valid @RequestBody CitaRequestDTO citaRequestDTO){
        CitaResponseDTO citaResponseDTO = service.crearCita(citaRequestDTO);
        if (citaResponseDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(citaResponseDTO);
    }

    // Respuesta a PUT
    @PutMapping("/{id}")
    public ResponseEntity<CitaResponseDTO> actualizarCita(@PathVariable Long id,
                                                                  @Valid @RequestBody CitaRequestDTO citaDTO){
        return service.actualizarCita(id, citaDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Respuesta a DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id){
        boolean eliminado = service.eliminarCita(id);
        if (eliminado)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}

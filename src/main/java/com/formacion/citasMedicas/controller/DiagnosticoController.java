package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.DiagnosticoRequestDTO;
import com.formacion.citasMedicas.dto.DiagnosticoResponseDTO;
import com.formacion.citasMedicas.service.DiagnosticoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoService service;

    @GetMapping
    public ResponseEntity<List<DiagnosticoResponseDTO>> listarDiagnosticos() {
        return ResponseEntity.ok(service.listarDiagnosticos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticoResponseDTO> obtenerDiagnostico(@PathVariable Long id){
        return service.obtenerDiagnostico(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DiagnosticoResponseDTO> crearDiagnostico(@Valid @RequestBody
                                                                       DiagnosticoRequestDTO diagnosticoDTO){
        DiagnosticoResponseDTO diagnostico = service.crearDiagnostico(diagnosticoDTO);
        if (diagnostico == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(diagnostico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticoResponseDTO> actualizarDiagnostico(@PathVariable Long id,
                                                                        @Valid @RequestBody DiagnosticoRequestDTO diagnosticoDTO){
        return service.actualizarDiagnostico(id, diagnosticoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDiagnostico(@PathVariable Long id){
        boolean eliminado = service.eliminarDiagnostico(id);
        if (eliminado)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}

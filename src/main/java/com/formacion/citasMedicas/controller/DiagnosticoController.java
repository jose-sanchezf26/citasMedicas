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
    public List<DiagnosticoResponseDTO> listarDiagnosticos() {
        return service.listarDiagnosticos();
    }

    @GetMapping("/{id}")
    public DiagnosticoResponseDTO obtenerDiagnostico(@PathVariable Long id){
        return service.obtenerDiagnostico(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiagnosticoResponseDTO crearDiagnostico(@Valid @RequestBody DiagnosticoRequestDTO diagnosticoDTO){
        return service.crearDiagnostico(diagnosticoDTO);
    }

    @PutMapping("/{id}")
    public DiagnosticoResponseDTO actualizarDiagnostico(@PathVariable Long id,
                                                                        @Valid @RequestBody DiagnosticoRequestDTO diagnosticoDTO){
        return service.actualizarDiagnostico(id, diagnosticoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarDiagnostico(@PathVariable Long id){
        service.eliminarDiagnostico(id);
    }
}

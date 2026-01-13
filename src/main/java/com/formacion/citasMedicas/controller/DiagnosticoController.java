package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.DiagnosticoRequestDTO;
import com.formacion.citasMedicas.dto.DiagnosticoResponseDTO;
import com.formacion.citasMedicas.service.DiagnosticoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Diagnosticos")
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoService service;

    @Operation(summary = "Obtener un resumen de todos los diagnosticos")
    @GetMapping
    public List<DiagnosticoResponseDTO> listarDiagnosticos() {
        return service.listarDiagnosticos();
    }

    @Operation(summary = "Obtener un diagnostico mediante su id")
    @GetMapping("/{id}")
    public DiagnosticoResponseDTO obtenerDiagnostico(@PathVariable Long id){
        return service.obtenerDiagnostico(id);
    }

    @Operation(summary = "Crear un diagnostico")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiagnosticoResponseDTO crearDiagnostico(@Valid @RequestBody DiagnosticoRequestDTO diagnosticoDTO){
        return service.crearDiagnostico(diagnosticoDTO);
    }

    @Operation(summary = "Actualizar un diagnostico mediante datos nuevos e id")
    @PutMapping("/{id}")
    public DiagnosticoResponseDTO actualizarDiagnostico(@PathVariable Long id,
                                                                        @Valid @RequestBody DiagnosticoRequestDTO diagnosticoDTO){
        return service.actualizarDiagnostico(id, diagnosticoDTO);
    }

    @Operation(summary = "Eliminar un diagnostico mediante su id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarDiagnostico(@PathVariable Long id){
        service.eliminarDiagnostico(id);
    }
}

package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.CitaRequestDTO;
import com.formacion.citasMedicas.dto.CitaResponseDTO;
import com.formacion.citasMedicas.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Citas")
@RequestMapping("/citas")
public class CitaController {

    private final CitaService service;

    @Operation(summary = "Obtener un resumen de todas las citas")
    @GetMapping
    public List<CitaResponseDTO> listarCitas(){
        return service.listarCitas();
    }

    @Operation(summary = "Obtener una cita mediante su id")
    @GetMapping("/{id}")
    public CitaResponseDTO obtenerCita(@PathVariable Long id){
        return service.obtenerCita(id);
    }

    @Operation(summary = "Crear una cita")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CitaResponseDTO crearCita(@Valid @RequestBody CitaRequestDTO citaRequestDTO){
        return service.crearCita(citaRequestDTO);
    }

    @Operation(summary = "Actualizar una cita mediante datos nuevos e id")
    @PutMapping("/{id}")
    public CitaResponseDTO actualizarCita(@PathVariable Long id,
                                                                  @Valid @RequestBody CitaRequestDTO citaDTO){
        return service.actualizarCita(id, citaDTO);
    }

    @Operation(summary = "Eliminar una cita")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCita(@PathVariable Long id){
        service.eliminarCita(id);
    }
}

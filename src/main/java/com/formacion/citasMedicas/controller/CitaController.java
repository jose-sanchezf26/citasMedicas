package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.CitaRequestDTO;
import com.formacion.citasMedicas.dto.CitaResponseDTO;
import com.formacion.citasMedicas.service.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/citas")
public class CitaController {

    private final CitaService service;

    @GetMapping
    public List<CitaResponseDTO> listarCitas(){
        return service.listarCitas();
    }

    @GetMapping("/{id}")
    public CitaResponseDTO obtenerCita(@PathVariable Long id){
        return service.obtenerCita(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CitaResponseDTO crearCita(@Valid @RequestBody CitaRequestDTO citaRequestDTO){
        return service.crearCita(citaRequestDTO);
    }

    @PutMapping("/{id}")
    public CitaResponseDTO actualizarCita(@PathVariable Long id,
                                                                  @Valid @RequestBody CitaRequestDTO citaDTO){
        return service.actualizarCita(id, citaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCita(@PathVariable Long id){
        service.eliminarCita(id);
    }
}

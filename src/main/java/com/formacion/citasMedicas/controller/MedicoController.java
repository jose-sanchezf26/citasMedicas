package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.MedicoRequestDTO;
import com.formacion.citasMedicas.dto.MedicoResponseDTO;
import com.formacion.citasMedicas.dto.MedicoResumenDTO;
import com.formacion.citasMedicas.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Medicos")
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    @Operation(summary = "Obtener un resumen de todos los medicos")
    @GetMapping
    public List<MedicoResumenDTO> listarMedicos(){
        return service.listarMedicos();
    }

    @Operation(summary = "Obtener un medico mediante su id")
    @GetMapping("/{id}")
    public MedicoResponseDTO obtenerMedico(@PathVariable Long id){
        return service.obtenerMedico(id);
    }

    @Operation(summary = "Crear un medico")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicoResponseDTO crearMedico(@Valid @RequestBody MedicoRequestDTO medicoDTO){
        return service.crearMedico(medicoDTO);
    }

    @Operation(summary = "Actualizar un medico mediante datos nuevos e id")
    @PutMapping("/{id}")
    public MedicoResponseDTO actualizarMedico(@PathVariable Long id,
                                                              @Valid @RequestBody MedicoRequestDTO medicoDTO){
        return service.actualizarMedico(id, medicoDTO);
    }

    @Operation(summary = "Eliminar un medico mediante su id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarMedico(@PathVariable Long id){
        service.eliminarMedico(id);
    }

}

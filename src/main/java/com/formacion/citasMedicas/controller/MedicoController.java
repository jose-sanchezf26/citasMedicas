package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.MedicoRequestDTO;
import com.formacion.citasMedicas.dto.MedicoResponseDTO;
import com.formacion.citasMedicas.dto.MedicoResumenDTO;
import com.formacion.citasMedicas.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    @GetMapping
    public List<MedicoResumenDTO> listarMedicos(){
        return service.listarMedicos();
    }

    @GetMapping("/{id}")
    public MedicoResponseDTO obtenerMedico(@PathVariable Long id){
        return service.obtenerMedico(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicoResponseDTO crearMedico(@Valid @RequestBody MedicoRequestDTO medicoDTO){
        return service.crearMedico(medicoDTO);
    }

    @PutMapping("/{id}")
    public MedicoResponseDTO actualizarMedico(@PathVariable Long id,
                                                              @Valid @RequestBody MedicoRequestDTO medicoDTO){
        return service.actualizarMedico(id, medicoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarMedico(@PathVariable Long id){
        service.eliminarMedico(id);
    }

}

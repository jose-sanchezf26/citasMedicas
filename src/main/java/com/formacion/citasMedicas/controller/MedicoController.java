package com.formacion.citasMedicas.controller;

import com.formacion.citasMedicas.dto.MedicoRequestDTO;
import com.formacion.citasMedicas.dto.MedicoResponseDTO;
import com.formacion.citasMedicas.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listarMedicos(){
        List<MedicoResponseDTO> medicos = service.listarMedicos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> obtenerMedico(@PathVariable Long id){
        return service.obtenerMedico(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> crearMedico(@Valid @RequestBody MedicoRequestDTO medicoDTO){
        MedicoResponseDTO medico = service.crearMedico(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> actualizarMedico(@PathVariable Long id,
                                                              @Valid @RequestBody MedicoRequestDTO medicoDTO){
        return service.actualizarMedico(id, medicoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id){
        boolean eliminado = service.eliminarMedico(id);
        if (eliminado)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}

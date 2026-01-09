package com.formacion.citasMedicas.repository;

import com.formacion.citasMedicas.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

    // Metodo para comprobar si existe un diagnóstico con el id de la cita indicada
    boolean existsByCitaId(Long citaId);

    // Metodo para comprobar si existe un diagnóstico con el id de la cita indicada Y no es el mismo que el entregado,
    // utilizado para actualizar el diagnóstico
    boolean existsByCitaIdAndIdNot(Long citaId, Long diagnosticoId);
}

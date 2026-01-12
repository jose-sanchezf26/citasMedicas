package com.formacion.citasMedicas.repository;

import com.formacion.citasMedicas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByUsuario(String usuario);
    boolean existsByUsuarioAndIdNot(Long id, String usuario);
}

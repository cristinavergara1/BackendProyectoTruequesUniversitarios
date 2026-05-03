package com.trueques.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trueques.backend.Entity.Propuesta;

@Repository
public interface PropuestaRepository extends JpaRepository<Propuesta, Long> {

    List<Propuesta> findByUsuarioOrigenId(Long usuarioId);

    List<Propuesta> findByUsuarioDestinoId(Long usuarioId);

    List<Propuesta> findByPublicacionId(Long publicacionId);
}

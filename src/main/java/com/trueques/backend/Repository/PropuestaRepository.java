package com.trueques.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trueques.backend.Entity.Propuesta;

import com.trueques.backend.Enums.EstadoPropuesta;

@Repository
public interface PropuestaRepository extends JpaRepository<Propuesta, Long> {

    List<Propuesta> findByUsuarioOrigenId(Long usuarioId);

    List<Propuesta> findByUsuarioDestinoId(Long usuarioId);

    List<Propuesta> findByPublicacionId(Long publicacionId);

    long countByUsuarioOrigenIdOrUsuarioDestinoId(Long origenId, Long destinoId);

    long countByEstadoAndUsuarioOrigenId(EstadoPropuesta estado, Long usuarioId);

    long countByEstadoAndUsuarioDestinoId(EstadoPropuesta estado, Long usuarioId);
}

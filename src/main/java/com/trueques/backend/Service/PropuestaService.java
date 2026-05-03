package com.trueques.backend.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trueques.backend.DTO.PropuestaRequestDTO;
import com.trueques.backend.Entity.Propuesta;
import com.trueques.backend.Entity.Publicacion;
import com.trueques.backend.Entity.Usuario;
import com.trueques.backend.Enums.EstadoPropuesta;
import com.trueques.backend.Repository.PropuestaRepository;
import com.trueques.backend.Repository.PublicacionRepository;
import com.trueques.backend.Repository.UsuarioRepository;

@Service
public class PropuestaService {

    @Autowired
    private PropuestaRepository propuestaRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✅ HU-005
    public Propuesta enviarPropuesta(Long usuarioId, PropuestaRequestDTO dto) {

        Publicacion publicacion = publicacionRepository.findById(dto.getPublicacionId())
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        // ❌ Regla: no enviar si está cerrada
        if (publicacion.isCerrada()) {
            throw new RuntimeException("La publicación ya está cerrada");
        }

        // ❌ Regla: no dinero
        if (dto.getOferta().toLowerCase().contains("dinero")) {
            throw new RuntimeException("No se permite dinero en el trueque");
        }

        Usuario origen = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Usuario destino = publicacion.getUsuario();

        Propuesta propuesta = new Propuesta();
        propuesta.setUsuarioOrigen(origen);
        propuesta.setUsuarioDestino(destino);
        propuesta.setPublicacion(publicacion);
        propuesta.setOferta(dto.getOferta());
        propuesta.setMensaje(dto.getMensaje());
        propuesta.setEstado(EstadoPropuesta.PENDIENTE);
        propuesta.setFechaCreacion(LocalDateTime.now());

        // 🔔 Notificación (simulada)
        System.out.println("Nueva propuesta recibida para usuario: " + destino.getCorreo());

        return propuestaRepository.save(propuesta);
    }

    // ✅ Consultar propuestas enviadas
    public List<Propuesta> obtenerPropuestasEnviadas(Long usuarioId) {
        return propuestaRepository.findByUsuarioOrigenId(usuarioId);
    }

    // ✅ HU-006: ver propuestas recibidas
    public List<Propuesta> obtenerPropuestasRecibidas(Long usuarioId) {
        return propuestaRepository.findByUsuarioDestinoId(usuarioId);
    }

    // ✅ HU-006: gestionar propuesta
    public Propuesta gestionarPropuesta(Long propuestaId, Long usuarioId, EstadoPropuesta nuevoEstado) {

        Propuesta propuesta = propuestaRepository.findById(propuestaId)
                .orElseThrow(() -> new RuntimeException("Propuesta no encontrada"));

        // ❌ Solo el dueño puede gestionar
        if (!propuesta.getUsuarioDestino().getId().equals(usuarioId)) {
            throw new RuntimeException("No autorizado");
        }

        propuesta.setEstado(nuevoEstado);
        propuesta.setFechaDecision(LocalDateTime.now());

        // ✅ Si acepta → cerrar publicación
        if (nuevoEstado == EstadoPropuesta.ACEPTADA) {
            Publicacion pub = propuesta.getPublicacion();
            pub.setCerrada(true);
            publicacionRepository.save(pub);

            // 🔔 Notificación
            System.out.println("Propuesta aceptada, notificar a: " +
                    propuesta.getUsuarioOrigen().getCorreo());
        }

        return propuestaRepository.save(propuesta);
    }
}
